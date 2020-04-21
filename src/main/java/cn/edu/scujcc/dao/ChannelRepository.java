package cn.edu.scujcc.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.edu.scujcc.model.Channel;

@Repository
public interface ChannelRepository extends MongoRepository<Channel,String>{
	List<Channel> findByTitleAndQuality(String t,String q);

	List<Channel> findByTitle(String title);

	List<Channel> findByQuality(String quality);
	//找出评论时间在指定日期之后的频道
	public List<Channel> findByCommentsDtAfter(LocalDateTime theDt);

	
}