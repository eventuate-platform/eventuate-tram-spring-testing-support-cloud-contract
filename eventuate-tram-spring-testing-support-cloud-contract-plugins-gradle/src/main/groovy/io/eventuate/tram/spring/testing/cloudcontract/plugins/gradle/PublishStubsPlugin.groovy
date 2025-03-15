package io.eventuate.tram.spring.testing.cloudcontract.plugins.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication

class PublishStubsPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        if (System.getenv('EXCLUDE_STUBS_FROM_PUBLISHING') != null) {
            System.out.println("excludeStubsFromPublishing")
            return;
        }
        project.apply(plugin: 'maven-publish')
        project.publishing {
            repositories {
                maven {
                    name = 'stubs'
                    url = project.uri(project.contractRepoDir)
                }
            }

            publications {
                stubs(MavenPublication) {
                    artifact project.verifierStubsJar

                    // https://github.com/spring-gradle-plugins/dependency-management-plugin/issues/273
                    versionMapping {
                        usage("java-api") {
                            fromResolutionOf("runtimeClasspath")
                        }
                        usage("java-runtime") {
                            fromResolutionResult()
                        }
                    }
                }
            }
        }
    }
}
