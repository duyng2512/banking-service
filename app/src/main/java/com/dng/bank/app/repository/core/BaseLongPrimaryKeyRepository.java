package vn.zalopay.rms.teststudioservice.repository;


import vn.zalopay.rms.teststudioservice.entity.BaseLongPrimaryKeyEntity;

public interface BaseLongPrimaryKeyRepository<T extends BaseLongPrimaryKeyEntity> extends BaseRepository<T, Long> {
}
