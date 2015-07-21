package com.kritcg.elastic;

import com.kritcg.elastic.entities.Blog;
import com.kritcg.elastic.entities.Tag;
import com.kritcg.elastic.repositories.BlogRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.elasticsearch.common.collect.Iterables;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.support.QueryParsers;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import static org.elasticsearch.index.query.FilterBuilders.*;
import static org.elasticsearch.index.query.QueryBuilders.*;

import java.io.IOException;
import java.util.*;
import java.util.logging.Filter;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Created by Chertpong on 7/21/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class RepositoryTests{
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Before
    @Test
    @Ignore
    public void shouldDeleteAllDataAndInitializeTestData(){
        //delete all data
        blogRepository.deleteAll();
        // create tags
        Tag tagScience = new Tag();
        tagScience.setId(1);
        tagScience.setName("วิทย์");
        Tag tagMath = new Tag();
        tagMath.setId(2);
        tagMath.setName("คณิต");

        //create blogs
        Blog b = new Blog();
        b.setId("1");
        b.setTitle("ไอบีเอ็มไตรมาสล่าสุด ภาพรวมรายได้ยังลดลง แต่ธุรกิจใหม่ยังเติบโตดี");
        b.setContent("ไอบีเอ็มรายงานผลประกอบการประจำไตรมาสที่ 2 ของปี 2015 มีรายได้รวม 20,813 ล้านดอลลาร์ ลดลง 13.5% เมื่อเทียบกับไตรมาสเดียวกันในปีก่อน อย่างไรไอบีเอ็มระบุว่าถ้าไม่คิดปัจจัยเรื่องเงินดอลลาร์แข็งค่า รายได้บริษัทจะลดลงเพียง 1% เท่านั้น ส่วนกำไรสุทธิอยู่ที่ 3,449 ล้านดอลลาร์ ลดลง 16.6%\n" +
                "แนวโน้มรายได้ในภาพรวมของไอบีเอ็มนั้นเป็นติดต่อกันมาหลายไตรมาสแล้วจากธุรกิจหลักเดิม อาทิ ฮาร์ดแวร์, ซอฟต์แวร์ และ Global Technology Services ที่ล้วนมีรายได้ลดลง หากแต่เมื่อพิจารณารายได้ส่วนธุรกิจใหม่ตามยุทธศาสตร์ที่ไอบีเอ็มวางไว้ (บริการกลุ่มเมฆ, Big Data Analytics, บริการบนมือถือ และบริการความปลอดภัย) ก็ยังเติบโตโดยมีรายได้รวมเพิ่มขึ้น 20% โดยเฉพาะบริการบนกลุ่มเมฆที่เติบโตกว่า 50%\n" +
                "ที่มา: ไอบีเอ็ม");
        b.setUrl("https://www.blognone.com/node/70551");
        b.setCreateDate(new Date());
        b.setTags(Arrays.asList(tagMath, tagScience));

        Blog b2 = new Blog();
        b2.setId("2");
        b2.setTitle("");
        b2.setContent("หลังจากมีข่าวลือว่า Yahoo กำลังซุ่มทำแอพส่งข้อความบนมือถือตัวใหม่อยู่ ล่าสุดทาง Yahoo ได้ปล่อยแอพ Yahoo Livetext แอพส่งข้อความลงบนวิดีโอใน iOS แล้ว เน้นเจาะตลาดกลุ่มวัยรุ่น\n" +
                        "จุดที่แตกต่างจากแอพส่งข้อความที่มีจำนวนมากในตอนนี้ อยู่ที่วิธีการส่งข้อความโดยมีวิดีโอเป็นพื้นหลังข้อความสนทนา และตัดเสียงของวิดีโอออก โดยนำแนวคิดและความเป็นที่นิยมของไฟล์ gif มาต่อยอด เพื่อเสนอทางเลือกให้กับผู้ใช้และนำเสนอประสบการณ์ในการถ่ายทอดอารมณ์และความรู้สึกได้มากกว่าการส่งข้อความแบบปกติ โดยตัวแอพเน้นไปที่การสนทนาแบบ 1-1 มากกว่าการสนทนาแบบกลุ่มอย่างที่คาดการณ์กันไว้ และยังไม่รองรับวิดีโอคอล\n" +
                        "ในตอนนี้ Yahoo เปิดให้โหลดได้เฉพาะ App Store ในฮ่องกง ซึ่งคาดว่าเป็นการทดสอบแอพก่อนการเปิดตัวอย่างเป็นทางการในเร็วๆ นี้ นอกจากนี้ยังได้ถอด Yahoo Messenger ออกจากแอพสโตร์ตั้งแต่เดือนพฤษภาคมที่ผ่านมา"
        );
        b2.setUrl("https://www.blognone.com/node/70550");
        b2.setCreateDate(new Date());
        b2.setTags(Arrays.asList(tagMath));


        Blog b3 = new Blog();
        b3.setId("3");
        b3.setTitle("ไมโครซอฟท์ออก ASP.NET 4.6 และ ASP.NET 5 Beta 5");
        b3.setContent("นอกจาก Visual Studio 2015 และ .NET 4.6 วันนี้ไมโครซอฟท์ยังออก ASP.NET สองเวอร์ชันรวด นั่นคือ ASP.NET 4.6 และ ASP.NET 5 Beta 5\n" +
                        "ASP.NET 4.6 เป็นการอัพเวอร์ชันของโมดูลย่อยยกชุด ได้แก่ Web Forms 4.6, MVC 5.2.3, Web Pages 3.2.3, Web API 5.2.3, SignalR 2.1.2 ส่วนฟีเจอร์ใหม่อย่างอื่นคือรองรับ .NET Compiler Platform (Roslyn), รองรับ HTTP/2, ปรับปรุงฟีเจอร์ของตัว Editor ทั้ง JavaScript/HTML/JSON, รองรับไฟล์ JXS ของ React.JS\n" +
                        "ส่วน ASP.NET 5 Beta 5 เป็นการปรับโครงสร้างครั้งใหญ่ของ ASP.NET ตามที่เคยประกาศไว้ โดยไมโครซอฟท์จะแยกโมดูลของ ASP.NET เป็นส่วนย่อยๆ ที่ไม่ต้องเรียกใช้งานทั้งหมด แถมยังทำงานได้ข้ามแพลตฟอร์มทั้งบนวินโดวส์ แมค ลินุกซ์ และมาพร้อมกับ ASP.NET MVC 6"
        );
        b3.setUrl("https://www.blognone.com/node/70548");
        b3.setCreateDate(new Date());
        b3.setTags(Arrays.asList(tagScience));

        //save 3 blogs
        blogRepository.save(Arrays.asList(b,b2,b3));

    }

    @Ignore
    @Before
    @Test
    public void shouldInitData(){
        List<Blog> blogList = new ArrayList<>();
        String url = "https://www.blognone.com/node/";
        for(int index = 70000; index<70500;index++){
        try {


                //init
                Document doc;
                doc = Jsoup.connect(url+index)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 " +
                                "(KHTML, like Gecko) Chrome/12.0.742.112 Safari/534.30").get();

                //processes
                Blog b = new Blog();
                b.setTitle(doc.select("h2[itemprop] > a").first().attr("title"));
                b.setUrl(url);
                b.setContent(doc.select(".node-content").text());
                b.setCreateDate(new Date());

                List<Tag> tags = new ArrayList<Tag>();
                int i = 0;
                for(Element e : doc.select(".terms > ul > li")){
                    Tag t =  new Tag();
                    t.setId(i);
                    t.setName(e.select("a[href]").text());
                    tags.add(t);
                    i++;
                }
                b.setTags(tags);

                //add to list
                blogList.add(b);
                System.out.println("Round"+index);


        }
        catch (HttpStatusException e) {
            e.printStackTrace();
        }
        catch (IOException io){
            io.printStackTrace();
        }
    }
        blogRepository.save(blogList);
        System.out.println("blogs: "+blogRepository.count());
    }
    @Test
    public void shouldPrintMapping(){
        Map map = elasticsearchTemplate.getMapping(Blog.class);
        System.out.println(map.toString());
    }

    @Test
    public void shouldGetAllBlogs(){
        List<Blog> blogList = Lists.newArrayList(blogRepository.findAll());
        blogList.forEach(b -> System.out.println(b.toString()));
    }

    @Test
    public void shouldGetBlogById(){
        assertThat(blogRepository.findOne("1").getUrl(), is("https://www.blognone.com/node/70551"));
    }

    @Test
    public void shouldCountAllBlogs(){
        assertThat(blogRepository.count(), is(3L));
    }

    @Test
    public void shouldGetBlogByKeywordLikeContent(){
        assertThat(blogRepository.findByContentLike("ไอบีเอ็ม").get(0).getId(), is(equalTo("1")));
    }
    @Ignore
    @Test
    public void shouldGetBlogByTag(){
        System.out.println("Start get by blog tag");
        //Wait for next phase
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(
                        QueryBuilders.filteredQuery(matchAllQuery(),FilterBuilders.nestedFilter("tags", termFilter("tags.id", 1)))
                )
                .build();

        System.out.println(query.getQuery().toString());
//        System.out.println(query.getFilter().toString());
        List<Blog> list = elasticsearchTemplate.queryForList(query,Blog.class);
        list.forEach(b-> System.out.println(b.toString()));
        assertThat(Iterables.size(list), greaterThan(0));

    }
    @Test
    public void shouldGetBlogByTiTleAndContent(){
        System.out.println("Start get by blog tags");
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(
                        QueryBuilders.multiMatchQuery("ธุรกิจ ไมโครซอฟท์ เปิดให้โหลดได้เฉพาะ", "title", "content")
                                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS)
                )
                .build();
        System.out.println(query.getQuery().toString());
        Iterable<Blog> list = blogRepository.search(query);
        list.iterator().forEachRemaining(s -> System.out.println(s.toString()));
        assertThat(Iterables.size(list), greaterThan(0));
    }


}
