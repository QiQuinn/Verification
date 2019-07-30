import com.qiquinn.verification.App;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/26
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserControllerTest
{
    @Autowired
    private WebApplicationContext webmvc;
    private MockMvc mockMvc;

    @Before
    public void startUp()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webmvc).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void whenUpLoadSuccess() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
        .file(
                new MockMultipartFile("file","text.txt","multipart/form-data","hellow upload".getBytes("UTF-8"))))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenQueryFail() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception
    {
        String content = "{\"username\":\"Tommy\",\"password\":\"123\",\"nikename\":\"Brid\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Tommy"));
    }
}
