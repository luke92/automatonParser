[![pipeline status](https://gitlab.com/luke92/automatonparser/badges/master/pipeline.svg)](https://gitlab.com/luke92/automatonparser/-/commits/master)
[![coverage report](https://gitlab.com/luke92/automatonparser/badges/master/coverage.svg)](https://luke92.gitlab.io/automatonparser/code-coverage-reports/)

# Requisitos
- Eclipse IDE
- Gradle 
- Maven

# Como importarlo?

- Hacer git clone del repositorio
- Abrir Eclipse
- File -> Import -> Gradle -> Existing Gradle Project
- Luego Next -> Next -> Seleccionar carpeta creada por el git clone -> Next -> Finish

# AutomatonParser

Proyecto de Teoria de la Computación de la Universidad Nacional de General Sarmiento<br/>
Año: 2020<br/>
Cuatrimestre: 1<br/>
Integrantes: Lucas Jonatan Vargas | Maximiliano Lucero Correa<br/>
Funcionalidades:
- Conversor de AFND a AFD desde archivo
- Implementación multithread del AFND para cada estado
- Parser no recursivo leyendo un archivo para la gramatica G (primero se crea la tabla de parsing, y luego se implementa el algoritmo )

# Consideraciones del TP 
(https://gitlab.com/luke92/automatonparser/-/blob/master/TP1C2020.pdf)

# AFND

[sımboloInput], [sımboloInput], ..., [sımboloInput]<br/>
[cantEstados]<br/>
[estadoFinal], [estadoFinal], ..., [estadoFinal]<br/>
[estado], [sımboloInput] -] [estado]<br/>
[estado], [sımboloInput] -] [estado]<br/><br/>
La primera lınea lista los elementos del alfabeto de input. La segunda indica la cantidad de estados, la tercera el conjunto de estados finales, y el resto de las lıneas la funci´on de transicion (los espacios extra en el archivo deben ignorarse).<br/><br/>
Ejemplo:<br/><br/>
a, b, c, d<br/>
10<br/>
1, 2, 3<br/>
1, a -> 3<br/>
1, a -> 4<br/>
1, a -> 5<br/>
1, d -> 4<br/><br/>
Asumimos que el estado 1 siempre sera el inicial, y que los estados estan numerados correlativamente de 1 a la cantidad de estados.

# Parser

El formato del archivo debe ser un conjunto de lıneas:<br/><br/>
[variable] -> [body]<br/><br/>
que representan las producciones de G, donde <br/>
[variable] es un string de la forma X_{i} con i un numero entero. y <br/>
[body] es un string conformado por variables como las indicamos recien y/o caracteres en minuscula, con los que denotamos los terminales de la gramatica. <br/>
Por ejemplo, esta serıa una lınea valida:<br/><br/>
X_{4} -> X_{34}aX_{1}bcd<br/><br/>
Asumimos que X_{1} es el s´ımbolo inicial