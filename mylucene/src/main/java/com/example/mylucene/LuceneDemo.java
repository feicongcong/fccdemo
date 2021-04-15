package com.example.mylucene;


import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.vectorhighlight.SimpleFieldFragList;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class LuceneDemo {
    public static void main(String[] args) {
//         addIndex();//添加索引

        searchIndex();// 检索索引

        // updateIdnex();//更新索引

        // deleteIndex();//删除索引
    }

    /**
     * 删除索引
     */
    private static void deleteIndex() {
        try {
            // 指定索引目录
            Directory directory = FSDirectory.open(Paths.get("E:\\lucenedemo\\lucene_db\\studen_tb"));

            // 创建分词器(单字)
            Analyzer analyzer = new SmartChineseAnalyzer();

            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            // 通过IndexWriterConfig指定索引的相关信息
            config.setOpenMode(OpenMode.CREATE_OR_APPEND);

            // 创建 更新 删除 都是IndexWriter来实现
            IndexWriter indexWriter = new IndexWriter(directory, config);

            // 删除索引
            indexWriter.deleteDocuments(new Term("studentID", "1"));

            // 提交事务
            indexWriter.commit();

            // 关闭流资源
            indexWriter.close();

            System.out.println("studnet luncene delete success !!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void updateIdnex() {
        try {
            // 指定索引目录
            Directory directory = FSDirectory.open(Paths.get("E:\\lucenedemo\\lucene_db\\studen_tb"));

            // 创建智能分词器
            Analyzer analyzer = new SmartChineseAnalyzer();

            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            // 通过IndexWriterConfig指定索引的相关信息
            config.setOpenMode(OpenMode.CREATE_OR_APPEND);

            // 创建 更新 删除 都是IndexWriter来实现
            IndexWriter indexWriter = new IndexWriter(directory, config);

            // 一个Document实例代表一条记录
            Document student = new Document();

            // StringField:不会对关键字进行分词
            student.add(new StringField("studentID", "1", Store.YES));
            student.add(new TextField("name", "李四", Store.YES));
            student.add(new TextField("scoreName", "Java", Store.YES));
            student.add(new TextField("score", "50", Store.YES));

            // 一个Document实例代表一条记录
            Document student2 = new Document();
            student2.add(new StringField("studentID", "2", Store.YES));
            student2.add(new TextField("name", "张三", Store.YES));
            student2.add(new TextField("scoreName", "Java", Store.YES));
            student2.add(new TextField("score", "60", Store.YES));

            // 通过IndexWriter 将数据更新至索引
            indexWriter.updateDocument(new Term("studentID", "1"), student);
            indexWriter.updateDocument(new Term("studentID", "2"), student2);
            // 提交事务
            indexWriter.commit();

            // 关闭流资源
            indexWriter.close();

            System.out.println("studnet luncene update success !!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchIndex() {
        try {
            // 指定索引目录
            Directory directory = FSDirectory.open(Paths.get("E:\\lucenedemo\\lucene_db\\studen_tb"));

            // 通过 DirectoryReader.open(directory) 读取的索引库信息,并返回相应的实例
            IndexReader indexReader = DirectoryReader.open(directory);

            // 通过 IndexSearcher 实例 来读取 数据
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            //智能分词器
            Analyzer analyzer = new SmartChineseAnalyzer();

            //单条件查询
            //QueryParser queryParser = new QueryParser("title", analyzer);

            //MultiFieldQueryParser 多条件查询
            QueryParser QueryParser = new MultiFieldQueryParser(new String[] {"cityName"}, analyzer);


            //======================条件过滤=====================
            //查询的字段 广州或者襄阳  1.空格:广州 襄阳   2.or:广州 OR 襄阳
            Query query = QueryParser.parse("广州 OR 襄阳");

            //查询 广州和襄阳 1.AND:广州 AND 襄阳  2.+：+广州 +襄阳
            //Query query = QueryParser.parse("+广州 +襄阳");

            //查询标题中包含广州
            //Query query = QueryParser.parse("cityName:广州");

            //查询城市名称是广州,但是描述中没有广州的数据 1.AND NOT 2.-
            //Query query = QueryParser.parse("cityName:广州 AND NOT cityIntroduce:广州");

            //==================================================

            /********************高亮相关代码******************************/

            //格式器,通过格式器对关键字进行处理
            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");

            //通过Scorer 包装 Query
            Scorer fragmentScorer = new QueryScorer(query);

            //创建高亮器
            Highlighter highlighter = new Highlighter(formatter, fragmentScorer);

            //创建格式化片段
            Fragmenter fragmenter = new SimpleFragmenter(10);

            //讲格式化片段实例和高亮器进行关联  最终通过高亮器对文本信息处理
            highlighter.setTextFragmenter(fragmenter);

            /********************高亮相关代码******************************/

            // 读取数据
            TopDocs search = indexSearcher.search(query, 100);

            // 查询到的数据的数目
            ScoreDoc[] scoreDocs = search.scoreDocs;

            System.out.println("student message :" + scoreDocs.length);

            for (int i = 0; i < scoreDocs.length; i++) {
                // 从索引表中拿到关联数据表的
                int id = scoreDocs[i].doc;

                Document doc = indexSearcher.doc(id);

				/*String studentID = doc.get("studentID");
				String name = doc.get("name");
				String scoreName = doc.get("scoreName");
				String score = doc.get("score");
				System.out.println("学生ID:" + studentID + " 学生姓名:" + name + " 学科名称:" + scoreName + " 学科成绩：" + score);*/

                String cityID = doc.get("cityID");
                String cityName = doc.get("cityName");
                String cityIntroduce = doc.get("cityIntroduce");

                //System.out.println("cityID: "+ cityID+" cityName: "+ cityName + " cityIntroduce: "+cityIntroduce);

                //分词器 字段 文本信息
                String PsotcityName = highlighter.getBestFragment(analyzer, "cityName", cityName);
                String PostcityIntroduce = highlighter.getBestFragment(analyzer, "cityIntroduce", cityIntroduce);


                System.out.println("PsotcityName: "+ PsotcityName + "   --   PostcityIntroduce: "+PostcityIntroduce);

            }

            System.out.println("studnet luncene Search success !!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addIndex() {
        try {
            // 指定索引目录
            Directory directory = FSDirectory.open(Paths.get("E:\\lucenedemo\\lucene_db\\studen_tb"));

            // 创建智能分词器
            Analyzer analyzer = new SmartChineseAnalyzer();

            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            // 通过IndexWriterConfig指定索引的相关信息
            config.setOpenMode(OpenMode.CREATE_OR_APPEND);

            // 创建 更新 删除 都是IndexWriter来实现
            IndexWriter indexWriter = new IndexWriter(directory, config);

            // 一个Document实例代表一条记录
            Document student = new Document();

            // StringField:不会对关键字进行分词
            student.add(new StringField("studentID", "1", Store.YES));
            student.add(new TextField("name", "李四", Store.YES));
            student.add(new TextField("scoreName", "Java", Store.YES));
            student.add(new TextField("score", "100", Store.YES));

            // 一个Document实例代表一条记录
            Document student2 = new Document();
            student2.add(new StringField("studentID", "2", Store.YES));
            student2.add(new TextField("name", "张三", Store.YES));
            student2.add(new TextField("scoreName", "Java", Store.YES));
            student2.add(new TextField("score", "90", Store.YES));

            // 一个Document实例代表一条记录
            Document city = new Document();
            city.add(new StringField("cityID", "1", Store.YES));
            city.add(new TextField("cityName", "广州", Store.YES));
            city.add(new TextField("cityIntroduce", "广州很好玩，有白云山著名旅游景点", Store.YES));

            // 一个Document实例代表一条记录
            Document city2 = new Document();
            city2.add(new StringField("cityID", "2", Store.YES));
            city2.add(new TextField("cityName", "襄阳", Store.YES));
            city2.add(new TextField("cityIntroduce", "襄阳有著名的广州投资企业", Store.YES));

            // 通过IndexWriter 将数据写入至索引库
            indexWriter.addDocument(student);
            indexWriter.addDocument(student2);
            indexWriter.addDocument(city);
            indexWriter.addDocument(city2);

            // 提交事务
            indexWriter.commit();

            // 关闭流资源
            indexWriter.close();

            System.out.println("studnet luncene create success !!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
