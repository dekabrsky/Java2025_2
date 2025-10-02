package ru.urfu.mapper;

public interface IMapper<Data, Domain> {
    Domain map(Data data);
}
