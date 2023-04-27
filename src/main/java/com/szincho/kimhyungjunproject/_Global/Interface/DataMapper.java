package com.szincho.kimhyungjunproject._Global.Interface;

public interface DataMapper<D, E> {

    D toDto(final E entity);
    E toEntity(final D dto);
}
