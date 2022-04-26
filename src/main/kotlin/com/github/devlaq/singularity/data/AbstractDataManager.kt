package com.github.devlaq.singularity.data

abstract class AbstractDataManager<K, V> {

    abstract fun get(key: K): V?
    abstract fun set(key: K, value: V)
    abstract fun has(key: K): Boolean
    abstract fun all(): Map<K, V>
    abstract fun key(): Collection<K>
    abstract fun value(): Collection<V>


}