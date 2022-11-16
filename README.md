# Golden Raspberry Awards

### Tecnologias utilizadas:
* Java 17
* SpringBoot 2.7.5
* Banco de dados H2
* Gradle
  
## 💻 Para executar o projeto:

Linux (terminal):
```
./gradlew bootRun
```

Windows (powershell):
```
.\gradlew bootRun
```

Se o Java 17 não estiver setado no JAVA_HOME, adicionar o parâmetro ```-Dorg.gradle.java.home=/DIRETORIO_JAVA_17```, ex:
```
./gradlew bootRun -Dorg.gradle.java.home=/usr/lib/jvm/java-1.17.0-openjdk-amd64
```


## ☕ Para utilizar a API:

```
http://localhost:8080/golden-raspberry-awards/min-max-interval
```

> Obs: o arquivo ```movielist.csv```, presente na pasta raíz do projeto, é carregado e persistido automaticamente após a inicialização do projeto.
