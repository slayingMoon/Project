package com.example.tsh.service;

import com.example.tsh.service.impl.OpenFolderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenFolderTest {
    @Autowired
    private OpenFolderServiceImpl openFolderService;

    @Test
    public void testDeletion(){
      openFolderService.deleteExpired();
    }
}
