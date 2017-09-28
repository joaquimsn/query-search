# Query-search
Make searching and pagination easy with Hibernate ORM using JPQL or SQL

[![Build Status](https://travis-ci.org/joaquimsn/query-search.svg?branch=master)](https://travis-ci.org/joaquimsn/query-search)
[![Coverage Status](https://coveralls.io/repos/github/joaquimsn/query-search/badge.svg?branch=master)](https://coveralls.io/github/joaquimsn/query-search?branch=master)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=com.github.joaquimsn:querysearch)](https://sonarcloud.io/dashboard/index/com.github.joaquimsn:querysearch)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.joaquimsn/querysearch.svg)](http://mvnrepository.com/artifact/com.github.joaquimsn/querysearch)

Query-search é uma lib criada para facilitar a implementação de consultas com paginação, construida em cima do ORM Hibernate com uma abordagem padronizada e flexível.

## Principais características
* Paginação com filtros dinamicos
* Suporte a consulta e paginação com query sql
* Retorno de objeto customizado para query nativas

## Instalação
Adicione a dependência no seu projeto
```xml
<dependency>
  <groupId>com.github.joaquimsn</groupId>
  <artifactId>querysearch</artifactId>
  <version>0.2.0</version>
</dependency>
```

## Como usar
1. Implemente a classe **SearchRepository** para prover uma instância do objeto Query
```java
public class SearchRepositoryImpl implements SearchRepository {
	
	...
	
	@Override
	public Query getQueryJpql(String jpql) {
		return entityManager.createQuery(jpql);
	}
	@Override
	public Query getQueryNativeQuery(String sqlQuery) {
		return entityManager.createNativeQuery(sqlQuery);
	}
}
```

2. Implemente a classe **SearchService**
```java
public class SearchServiceImpl implements SearchService {
	
	...
	
	@Override
	public SearchRepository getRepository() {
		return repository;
	}
}
```

3. Agora é só implementar um filtro, use **AbstractSpqlSearchFilter** para paginação e consultas com sql, ou **AbstractJpqlSearchFilter** para trabalhar com entidades mapeadas

## Veja um exemplo
[Clique aqui para acessar os exemplos](https://github.com/joaquimsn/query-search/tree/master/examples) 
