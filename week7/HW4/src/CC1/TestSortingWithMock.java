package CC1;

import org.jmock.Expectations;
import org.junit.Test;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;


public class TestSortingWithMock {

    @Test
    public void testSort() {

        Mockery context =new JUnit4Mockery();

        final Sorter sorter =context.mock(Sorter.class);
        FindMaxUsingSorting find =new FindMaxUsingSorting();

        final int[] inputArray =new int[]{1, 4, 3, 5};

        context.checking(new Expectations(){{
            oneOf(sorter).sort(inputArray);
            will(returnValue(new int[]{1, 3, 4, 5}));
        }});


        find.findmax(inputArray, sorter);

        context.assertIsSatisfied();
    }
}
