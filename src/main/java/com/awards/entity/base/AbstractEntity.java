package com.awards.entity.base;

/**
 * Classe abstrata para padronização dos atributos obrigatórios e possivelmente métodos futuros já resolvidos
 * para entities.
 * @param <ID>
 */
public abstract class AbstractEntity<ID> implements EntityInterface<ID> {

    private ID id;
}
