package com.incode.companyregistryservice;


import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaPackage;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.metrics.ArchitectureMetrics;
import com.tngtech.archunit.library.metrics.ComponentDependencyMetrics;
import com.tngtech.archunit.library.metrics.LakosMetrics;
import com.tngtech.archunit.library.metrics.MetricsComponents;
import com.tngtech.archunit.library.plantuml.rules.PlantUmlArchCondition;
import lombok.extern.slf4j.Slf4j;


import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@AnalyzeClasses(packagesOf = {CompanyRegistryServiceApplicationTests.class},
        importOptions = {ImportOption.DoNotIncludeTests.class})
class ArchitectureFitnessTest {

    private static final String ROOT_PACKAGE = "com.incode.companyregistryservice";

    private static final String REST_API_PACKAGE = ROOT_PACKAGE + ".api";

    private static final String DTO_API_PACKAGE = ROOT_PACKAGE + ".dto";

    @ArchTest
    void testCycles(JavaClasses classes) {

        slices().matching(ROOT_PACKAGE + ".(*)..").should().beFreeOfCycles().check(classes);
    }

    @ArchTest
    void testAdheresToPlantUmlDiagram(JavaClasses classes) {

        final URL packagesDiagram = getClass().getResource("/packages-diagram.puml");
        assertNotNull(packagesDiagram);
        classes().should(PlantUmlArchCondition.adhereToPlantUmlDiagram(packagesDiagram, PlantUmlArchCondition.Configuration.consideringOnlyDependenciesInDiagram()))
                .check(classes);
    }

    @ArchTest
    void testCumulativeDependencyMetrics(JavaClasses classes) {

        final Set<JavaPackage> packages = getJavaPackages(classes);
        final MetricsComponents<JavaClass> components = MetricsComponents.fromPackages(packages);
        final LakosMetrics metrics = ArchitectureMetrics.lakosMetrics(components);

        log.info("CCD: {}",  metrics.getCumulativeComponentDependency());
        log.info("ACD: {}", metrics.getAverageComponentDependency());
        log.info("RACD: {}", metrics.getRelativeAverageComponentDependency());
        log.info("NCCD: {}", metrics.getNormalizedCumulativeComponentDependency());
    }

    @ArchTest
    void testComponentDependencyMetrics(JavaClasses classes) {

        final MetricsComponents<JavaClass> components = MetricsComponents.fromPackages(getJavaPackages(classes));
        final ComponentDependencyMetrics metrics = ArchitectureMetrics.componentDependencyMetrics(components);

        components.forEach(javaClasses -> {
            String componentName = javaClasses.getIdentifier();
            log.info("---------------------------------------");
            log.info("[" + componentName + "]");
            log.info("Ce: {}", metrics.getEfferentCoupling(componentName));
            log.info("Ca: {}", metrics.getAfferentCoupling(componentName));
            log.info("I: {}", metrics.getInstability(componentName));
            log.info("A: {}", metrics.getAbstractness(componentName));
            log.info("D: {}", metrics.getNormalizedDistanceFromMainSequence(componentName));
            log.info("---------------------------------------");
            // Components with D > 0.7 can be problematic
            assertThat("NormalizedDistanceFromMainSequence [" + componentName + "] should be < 0.7",
                    metrics.getNormalizedDistanceFromMainSequence(componentName), lessThanOrEqualTo(0.7));
        });
    }

    private Set<JavaPackage> getJavaPackages(JavaClasses classes) {

        return classes.getPackage(ROOT_PACKAGE).getSubpackages().stream()
                .filter(javaPackage -> !REST_API_PACKAGE.equals(javaPackage.getName()) && !DTO_API_PACKAGE
                        .equals(javaPackage.getName()))
                .collect(Collectors.toSet());
    }
}