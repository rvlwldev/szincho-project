package com.szincho.kimhyungjunproject._Global.Interface;

public interface DataMapper<D, E> {

    default D toDto(final E entity) {return null;}
    default E toEntity(final D dto) {return null;}
}
