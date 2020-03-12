package life.motherbaby.community.controller;

import life.motherbaby.community.dto.AccessTokenDTO;
import life.motherbaby.community.dto.GithubUser;
import life.motherbaby.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    //接受登录请求GitHub得参数
    public  String callback(@RequestParam(name="code") String code,
                            @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("b24e75ad57134e045beb");
        accessTokenDTO.setClient_secret("1d37a3f9df668293fcba02c2bc6c78f83fbccd84");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
