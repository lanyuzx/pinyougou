package cn.lanlan.core.userDetailService;

import cn.lanlan.core.pojo.seller.Seller;
import cn.lanlan.core.service.LLSellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LLUserDetailServiceImpl implements UserDetailsService {
    private LLSellerService sellerService;

    public void setSellerService(LLSellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.isEmpty(username)) {
            Seller seller = sellerService.findSellerOneById(username);

            if (seller != null ) {
                if (seller.getStatus().equals("1")) {
                    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
                    return new User(username, seller.getPassword(), grantedAuthorities);
                }else  {
                    return  null;
                }

            }else  {
                return  null;
            }

        }
        return  null;
    }
}
