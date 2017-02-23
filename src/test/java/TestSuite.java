

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.lonsec.nm.common.ConstantsTest;
import com.lonsec.nm.processor.ExcessProcessorTest;
import com.lonsec.nm.processor.HelperTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
       ExcessProcessorTest.class,
       HelperTest.class,
       ConstantsTest.class
})
public class TestSuite {

}
