package com.spiri.themes.repository;

import com.spiri.themes.domain.UserPreference;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPreferenceRepository extends PagingAndSortingRepository<UserPreference, String> {

}
