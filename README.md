# Gestion Hotelera - BK Programacion

Proyecto Java demostrador del **Tema 4: Optimizacion y Documentacion** del
modulo *Entornos de Desarrollo* (DAW). Cubre los 9 criterios de la rubrica:

| Crit. | Concepto | Evidencia |
|-------|----------|-----------|
| a | Patrones de refactorizacion | `legacy/` vs `src/main/java/` |
| b | Pruebas de la refactorizacion | `src/test/java/` (JUnit 5) |
| c | Analizador de codigo | `mvn pmd:pmd` => `target/site/pmd.html` |
| d | Configuracion del analizador | `config/pmd-ruleset.xml` |
| e | Aplicar patrones con el IDE | Ver memoria PDF |
| f | Control de versiones integrado | `git log` (carpeta `.git`) |
| g | Repositorio remoto | `https://github.com/Sutrequito/GestionHoteleria` |
| h | Integracion continua | `.github/workflows/ci.yml` |
| i | Documentacion de clases | `mvn javadoc:javadoc` => `target/site/apidocs/` |

## Compilar y ejecutar

```bash
mvn compile           # compila
mvn test              # ejecuta JUnit
mvn pmd:pmd           # analisis PMD
mvn javadoc:javadoc   # genera JavaDoc
mvn package           # JAR final
```

## Autor

Rodri (Sutrequito) - Mayo 2026
