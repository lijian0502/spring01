
package cn.edu.scujcc.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;

/**
 * 提供频道相关业务逻辑。
 * @author asus
 *
 */
@Service
public class ChannelService {
		@Autowired
		private ChannelRepository repo;
		
//		public List<Channel> getAllChannels(){
//			return repo.findAll();
//		}
//		
		
		
		/**
		 * 获取一个频道
		 * @param id
		 * @return
		 */
		public Channel getChannel(String channelId) {
			Optional<Channel> result = repo.findById(channelId);
			
			if(result.isPresent()) {
				return result.get();
			}else {
			return null;
			}
		}
		
		/**
		 * 获取所有频道
		 */
		public List<Channel> getAllChannels(){
//			repo.findByTitleContaining("中央"，PageRequest.of(0, 10));
//			return page.toList();
			return repo.findAll();
		}
		
		/**
		 * 删除指定频道
		 * @param id
		 * @return
		 */
		public boolean deleteChannel(String channelId) {
			boolean result = true;
			repo.deleteById(channelId);
			
			return result;
		}

		
		/**
		 * 更新一个频道
		 * @param c 待更新的频道
		 * @return 更新后的频道
		 */
		public Channel updateChannel(Channel c) {
			Channel saved = getChannel(c.getId());
			if (saved != null) {
				if (c.getTitle() != null) {
					saved.setTitle(c.getTitle());
				}
				if (c.getQuality() != null) {
					saved.setQuality(c.getQuality());
				}
				if (c.getUrl() != null) {
					saved.setUrl(c.getUrl());
				}
				if (c.getComments() != null) {
					saved.getComments().addAll(c.getComments());
				} else {
					saved.setComments(c.getComments());
				}
			}
			return repo.save(saved);
		}
		
		/**
		 * 
		 * @param c
		 * @return
		 */
		public Channel createChannel(Channel c) {
			return repo.save(c);
			/*c.setId(this.channels.get(this.channels.size()-1).getId()+1);
			this.channels.add(c);
			return c;*/
			
		}
		
		public List<Channel> search(String title,String quality){
			 return repo.findByTitleAndQuality(title, quality);
			
		}
		public List<Channel> searchbiaoti(String title){
			return repo.findByTitle(title);
		}
		public List<Channel> searchzhiliang(String quality){
			return repo.findByQuality(quality);
		}
		/**
		 * 找出今天有评论的频道
		 * @return
		 */
		public List<Channel> getLatestCommentsChannel(){
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime today = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 0, 0);
			return repo.findByCommentsDtAfter(today);
		}
}