plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.fourqube"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.apache.ignite:ignite-core:2.16.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// Для клиента ignite необходимо добавить следующие VM options:
tasks {
	test {
		jvmArgs("--add-opens=java.base/jdk.internal.misc=ALL-UNNAMED",
			"--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
			"--add-opens=java.management/com.sun.jmx.mbeanserver=ALL-UNNAMED",
			"--add-opens=jdk.internal.jvmstat/sun.jvmstat.monitor=ALL-UNNAMED",
			"--add-opens=java.base/sun.reflect.generics.reflectiveObjects=ALL-UNNAMED",
			"--add-opens=jdk.management/com.sun.management.internal=ALL-UNNAMED",
			"--add-opens=java.base/java.io=ALL-UNNAMED",
			"--add-opens=java.base/java.nio=ALL-UNNAMED",
			"--add-opens=java.base/java.util=ALL-UNNAMED",
			"--add-opens=java.base/java.lang=ALL-UNNAMED")
	}
}
