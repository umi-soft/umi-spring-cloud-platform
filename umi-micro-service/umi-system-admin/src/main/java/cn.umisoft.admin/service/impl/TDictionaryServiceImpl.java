package cn.umisoft.admin.service.impl;

import cn.umisoft.admin.entity.TDictionary;
import cn.umisoft.admin.mapper.TDictionaryMapper;
import cn.umisoft.admin.repository.TDictionaryRepository;
import cn.umisoft.admin.service.ITDictionaryService;
import cn.umisoft.web.service.impl.UmiServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务字典（含业务字典分类）表 服务实现类
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-21
 */
@Service
public class TDictionaryServiceImpl extends UmiServiceImpl<TDictionaryMapper, TDictionaryRepository, TDictionary> implements ITDictionaryService {

}
