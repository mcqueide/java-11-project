package br.com.arqdev.util;

import static org.dozer.loader.api.TypeMappingOptions.mapNull;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Conversor {

	private Mapper mapper;

	@Autowired
	public Conversor(Mapper mapper) {
		this.mapper = mapper;
	}

	public <S, T> T converter(S entidade, Class<T> tipoVO) {

		if (entidade == null) {

			return null;
		}

		return mapper.map(entidade, tipoVO);
	}

	public <S, T> T converter(S entidade, Class<T> tipoVO, BeanMappingBuilder builder) {

		if (entidade == null) {

			return null;
		}

		((DozerBeanMapper) mapper).addMapping(builder);

		return mapper.map(entidade, tipoVO);
	}

	public <S, T> T converter(final S entidade, final Class<T> tipoVO, final String... excludeFields) {

		if (entidade == null) {

			return null;
		}

		BeanMappingBuilder builder = new BeanMappingBuilder() {

			@Override
			protected void configure() {

				TypeMappingBuilder file = mapping(entidade.getClass(), tipoVO, TypeMappingOptions.oneWay(),
						mapNull(true));

				for (String field : excludeFields) {

					file.exclude(field);
				}
			}
		};
		return converter(entidade, tipoVO, builder);
	}
	
}
