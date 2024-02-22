package com.jditeam2.modulesson.service;

import com.jditeam2.modulesson.domain.Item;
import com.jditeam2.modulesson.domain.ItemImg;
import com.jditeam2.modulesson.dto.ItemForm;
import com.jditeam2.modulesson.repository.ItemImgRepository;
import com.jditeam2.modulesson.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;

    public Long saveItem(ItemForm itemForm, List<MultipartFile> itemImgFileList) throws Exception {
        Item item = itemForm.toEntity(itemForm);
        itemRepository.save(item);

        for (int i = 0, max = itemImgFileList.size(); i < max; i++) {
            ItemImg itemImg = ItemImg.builder()
                    .item(item)
                    .repImgYn(i == 0 ? "Y" : "N")
                    .build();
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }
}
