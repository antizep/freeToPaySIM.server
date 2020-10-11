package ru.ccoders.model;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultRound {
	ItemModel useEnemy;
	boolean isFinal;
	List<ItemModel> trophy;
	

}
