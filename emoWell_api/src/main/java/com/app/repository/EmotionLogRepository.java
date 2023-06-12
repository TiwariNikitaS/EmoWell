package com.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.EmotionLog;

@Repository
public interface EmotionLogRepository extends JpaRepository<EmotionLog, Integer> {

	List<EmotionLog> findByOrderByDateAsc();
	EmotionLog findByDate(Date date);
}
