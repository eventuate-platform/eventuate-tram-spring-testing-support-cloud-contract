package io.eventuate.tram.spring.testing.cloudcontract.plugins.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigureContractRepoDirPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.ext {
            set('contractRepoDir', "${project.rootDir}/build/repos/contracts")
            set('contractRepoUrl', "file://${project.contractRepoDir}")
        }

    }
}