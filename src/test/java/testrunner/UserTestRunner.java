package testrunner;

import controller.User;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestRunner {

    @Test(priority = 0, description = "User Do Not Login With Invalid Email")
    public void userDoNotLoginWithInvalidEmail() throws ConfigurationException, IOException {
        User user = new User();
        user.userCanNotLoginWithInvalidEmail("salman@roadtocareer.ne","1234");
        String messageExpected="User not found";
        Assert.assertEquals(user.getMessage(),messageExpected);
        System.out.println(messageExpected);
    }

    @Test(priority = 1, description = "User Do Not Login With Invalid Password")
    public void userDoNotLoginWithInvalidPassword() throws ConfigurationException, IOException {
        User user = new User();
        user.userCanNotLoginWithInvalidPassword("salman@roadtocareer.net","1255");
        String messageExpected="Password incorrect";
        Assert.assertEquals(user.getMessage(),messageExpected);
        System.out.println(messageExpected);
    }

    @Test(priority = 2, description = "User Can Not Login With Empty Data")
    public void userCanNotLoginWithEmptyData() throws ConfigurationException, IOException {
        User user = new User();
        user.userCanNotLoginWithEmptyData("","");
        String messageExpected="User not found";
        Assert.assertEquals(user.getMessage(),messageExpected);
        System.out.println(messageExpected);
    }

    @Test(priority = 3, description = "User can Login With valid Credential")
    public void doLogin() throws ConfigurationException, IOException {
        User user=new User();
        user.userLoginWithValidCredential("salman@roadtocareer.net","1234");
        String messageExpected="Login successfully";
        Assert.assertEquals(user.getMessage(),messageExpected);
        System.out.println(messageExpected);
    }

    @Test(priority = 4, description = "Get the user list")
    public void getUserList() throws IOException {
        User user=new User();
        String id= user.callingUserListAPI();
        System.out.println(id);
        Assert.assertEquals(id,String.valueOf(8960));
    }
}
