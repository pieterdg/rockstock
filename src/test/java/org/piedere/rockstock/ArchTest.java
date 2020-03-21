package org.piedere.rockstock;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.piedere.rockstock");

        noClasses()
            .that()
                .resideInAnyPackage("org.piedere.rockstock.service..")
            .or()
                .resideInAnyPackage("org.piedere.rockstock.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..org.piedere.rockstock.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
