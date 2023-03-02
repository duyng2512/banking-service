package com.dng.bank.app.repository.core;


import com.dng.bank.app.entity.core.BaseLongPrimaryKeyEntity;

public interface BaseLongPrimaryKeyRepository<T extends BaseLongPrimaryKeyEntity> extends BaseRepository<T, Long> {
}
