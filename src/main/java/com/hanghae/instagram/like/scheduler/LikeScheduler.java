package com.hanghae.instagram.like.scheduler;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import com.hanghae.instagram.comment.repository.CommentRepository;
import com.hanghae.instagram.like.repository.CommentLikeRepository;
import com.hanghae.instagram.like.repository.PostingLikeRepository;
import com.hanghae.instagram.posting.repository.PostingRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LikeScheduler {

	private final PostingLikeRepository postingLikeRepository;
	private final CommentLikeRepository commentLikeRepository;
	private final PostingRepository postingRepository;
	private final CommentRepository commentRepository;

	@Bean
	public LockProvider lockProvider(DataSource dataSource) {
		return new JdbcTemplateLockProvider(dataSource);
	}

	private static final String SEC_30 = "PT30S";

	@Transactional
	@SchedulerLock(name = "runScenarioOneTime", lockAtMostFor = SEC_30, lockAtLeastFor = SEC_30)
	@Scheduled(cron = "0 30 4 L * ?")
	public void LikeCountSch() {

		List<LikeDto> newPostLikeCountList = postingLikeRepository.likeCountBatch();
		newPostLikeCountList.stream()
			.forEach(p -> postingRepository.updateLikeCount(p.getId(), p.getLikeCount()));

		List<LikeDto>  newCommentLikeCountList = commentLikeRepository.likeCountBatch();
		newCommentLikeCountList.stream()
			.forEach(c -> commentRepository.updateLikeCount(c.getId(), c.getLikeCount()));
	}

}
