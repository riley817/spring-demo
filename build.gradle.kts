import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "2.2.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.61"
	kotlin("kapt") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
}

allprojects {
	repositories {
		jcenter()
	}
}

subprojects {
	apply(plugin = "kotlin")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")

	group = "io.spring.demo"
	version = "0.0.1-SNAPSHOT"
	java.sourceCompatibility = JavaVersion.VERSION_11

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter")
		implementation("org.springframework.boot:spring-boot-starter-web")

		// Lombok
		annotationProcessor ("org.projectlombok:lombok:1.18.4")
		implementation      ("org.projectlombok:lombok:1.18.4")

		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		testImplementation("org.springframework.boot:spring-boot-starter-test") {
			exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		}
	}

	repositories {
		mavenCentral()
	}


	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.11"
		}
	}
}

project("module-domain") {
	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("pl.allegro.tech.boot:handlebars-spring-boot-starter:0.3.0")
		implementation("com.h2database:h2")
	}

	val jar: Jar by tasks
	val bootJar: BootJar by tasks

	bootJar.enabled = false
	jar.enabled = true
}

project("module-api") {

	dependencies {
		implementation(project(":module-domain"))
	}
}


