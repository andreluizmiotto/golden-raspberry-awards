# Golden Raspberry Awards

Tecnologias:
  * Java 17
  * SpringBoot 2.7.5
  * Banco de dados H2
  * Gradle
  
Para executar o projeto pelo terminal (necessário Java 17 setado no JAVA_HOME):
  * bash gradlew bootRun
  
Se o Java 17 não estiver setado no JAVA_HOME:
  * bash gradlew bootRun -Dorg.gradle.java.home=/CAMINHO_JAVA_17
    * bash gradlew bootRun -Dorg.gradle.java.home=/usr/lib/jvm/java-1.17.0-openjdk-amd64
  
