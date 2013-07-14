/*
 * Copyright 2013 Jason Heithoff
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.tomcat.nio=true
//grails.project.war.file = "target/${appName}-${appVersion}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

//grails.config.base.webXml = "file:${basedir}/web-app/WEB-INF/atmosphere-cache.xml"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.

        // runtime 'mysql:mysql-connector-java:5.1.22'
		compile "org.grails:grails-webflow:$grailsVersion"
		
		compile('org.apache.activemq:activemq-core:5.3.0',
			'org.apache.activemq:activeio-core:3.1.2',
			'org.apache.xbean:xbean-spring:3.7') {
		  excludes 'activemq-openwire-generator'
		  excludes 'commons-logging'
		  excludes 'xalan' // IVY-1006 - use xalan 2.7.0 to avoid (see below)
		  excludes 'xml-apis' // GROOVY-3356
		  exported = false
			}

        //compile ":tomcatnio:1.3.4"
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.3"
		compile ":jquery-ui:1.8.24"
        runtime ":resources:1.1.6"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.5"

        //build ":tomcat:$grailsVersion"
        compile ":jetty:2.0.3"

        runtime ":database-migration:1.3.2"

        compile ':cache:1.0.1'
		
		compile ':webflow:2.0.8.1', {
			exclude 'grails-webflow'
		  }
		
		//compile ":authentication:2.0.1" replaced by Grail's Spring Security Core Plugin
		compile ":spring-security-core:1.2.7.3" 
		//compile ":spring-security-ldap:1.0.6"
		
		//build ':jbossas:1.0'
		
		compile ":famfamfam:1.0.1"
		
		compile ":atmosphere:0.4.2.1"
		compile ":activemq:0.4.1"
		compile ":jms:1.2"
    }
}
