package uk.ac.kcl.sufcwmillionapplication.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.List;

import uk.ac.kcl.sufcwmillionapplication.api.impl.YahooShareDaoImpl;
import uk.ac.kcl.sufcwmillionapplication.bean.DailyQuote;
import uk.ac.kcl.sufcwmillionapplication.bean.SearchBean;
import uk.ac.kcl.sufcwmillionapplication.bean.SymbolInfo;

public class YahooShareDaoImplTest {

    private SearchBean searchBean;
    private SearchBean searchBeanFx;

    @Before
    public void setUp(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            searchBean = new SearchBean("0005.HK",sdf.parse("2020-12-01"),
                    sdf.parse("2021-01-05"));
            searchBeanFx = new SearchBean("GBPUSD=X",sdf.parse("2019-01-01"),
                    sdf.parse("2020-12-31"));
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void getInfoOfSymbol() {
        ShareDao yahooShareDao = new YahooShareDaoImpl();
        SymbolInfo symbolInfo = yahooShareDao.getInfoOfSymbol(searchBean);
        System.out.println(symbolInfo);
        Assert.assertNotNull(symbolInfo);
        Assert.assertEquals("HSBC Holdings plc", symbolInfo.getLongName());
        Assert.assertEquals("HSBC HOLDINGS", symbolInfo.getShortName());
        Assert.assertEquals("0005.HK",symbolInfo.getSymbol());
    }

    @Test
    public void getInfoOfFX() {
        ShareDao yahooShareDao = new YahooShareDaoImpl();
        SymbolInfo symbolInfo = yahooShareDao.getInfoOfSymbol(searchBeanFx);
        System.out.println(symbolInfo);
        Assert.assertNotNull(symbolInfo);
        Assert.assertEquals("GBP/USD", symbolInfo.getLongName());
        Assert.assertEquals("GBP/USD", symbolInfo.getShortName());
        Assert.assertEquals("GBPUSD=X",symbolInfo.getSymbol());
    }

    @Test
    public void getHistoryQuotes() throws Exception {
        ShareDao yahooShareDao = new YahooShareDaoImpl();

        List<DailyQuote> quotes = yahooShareDao.getHistoryQuotes(searchBean);
        Assert.assertNotNull(quotes);
        Assert.assertEquals(24, quotes.size());
    }

    @Test
    public void getHistoryQuotesFX() throws Exception {
        ShareDao yahooShareDao = new YahooShareDaoImpl();

        List<DailyQuote> quotes = yahooShareDao.getHistoryQuotes(searchBeanFx);
        Assert.assertNotNull(quotes);
    }
}
