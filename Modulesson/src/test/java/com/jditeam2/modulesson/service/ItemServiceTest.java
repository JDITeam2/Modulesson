package com.jditeam2.modulesson.service;

import com.jditeam2.modulesson.domain.Item;
import com.jditeam2.modulesson.domain.ItemImg;
import com.jditeam2.modulesson.domain.ItemSellStatus;
import com.jditeam2.modulesson.dto.ItemForm;
import com.jditeam2.modulesson.repository.ItemImgRepository;
import com.jditeam2.modulesson.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.config.location=classpath:application-test.yml")
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception {
        List<MultipartFile> multipartFileList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String path = "/Users/~";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }
    @Test
    @DisplayName("상품 등록 테스트")
//    @WithMockUser(username = "expert", roles = "EXPERT")
    public void saveItem() throws Exception {
        ItemForm itemForm = ItemForm.builder()
                .itemName("테스트 상품")
                .itemSellStatus(ItemSellStatus.SELL)
                .itemDetail("테스트 상품입니다.")
                .price(1000)
                .stockNumber(10)
                .build();
        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long itemId = itemService.saveItem(itemForm, multipartFileList);

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

        assertEquals(itemForm.getItemName(), item.getItemName());
        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriginalImgName());

    }
}