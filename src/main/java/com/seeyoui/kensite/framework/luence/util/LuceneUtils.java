package com.seeyoui.kensite.framework.luence.util;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.flexible.standard.nodes.RegexpQueryNode;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.seeyoui.kensite.framework.luence.domain.LuceneDocument;

public class LuceneUtils {

	private static final Analyzer analyzer = new SmartChineseAnalyzer();// 中文词库分词
	
	public static boolean create(String indexPath, List<LuceneDocument> documentList) throws Exception {
		FSDirectory fsDir = FSDirectory.open(Paths.get(indexPath));
		// 1.启动时读取原有磁盘索引文件
//		Directory ramDir = new RAMDirectory(fsDir, new IOContext());
//		IndexWriterConfig config = new IndexWriterConfig(analyzer);
//		IndexWriter ramIndexWiter = new IndexWriter(ramDir, config);

		// 3.退出时将内存索引保存到磁盘索引中
		IndexWriterConfig indexConfig = new IndexWriterConfig(analyzer);
		IndexWriter fsIndexWriter = new IndexWriter(fsDir, indexConfig);
		// 2.添加 Document
		for(LuceneDocument document : documentList) {
			Document doc = new Document();
			doc.add(new Field("id", document.getId(), StringField.TYPE_STORED));
			doc.add(new Field("content", document.getContent(), StringField.TYPE_STORED));
			fsIndexWriter.addDocument(doc);
//			ramIndexWiter.addDocument(doc);
		}
//		ramIndexWiter.close();
		
//		fsIndexWriter.addIndexes(ramDir);
		fsIndexWriter.close();
		return true;
	}
	
	public static boolean clean(String indexPath) throws Exception {
		FSDirectory fsDir = FSDirectory.open(Paths.get(indexPath));
		// 1.清空所有内存索引
//		Directory ramDir = new RAMDirectory(fsDir, new IOContext());
//		IndexWriterConfig config = new IndexWriterConfig(analyzer);
//		IndexWriter ramIndexWiter = new IndexWriter(ramDir, config);
//		ramIndexWiter.deleteAll();
//		ramIndexWiter.close();
		// 1.清空所有硬盘索引
		IndexWriterConfig indexConfig = new IndexWriterConfig(analyzer);
		IndexWriter fsIndexWriter = new IndexWriter(fsDir, indexConfig);
		fsIndexWriter.deleteAll();
		fsIndexWriter.close();
		return true;
	}
	
	public static boolean insert(String indexPath, LuceneDocument document) throws Exception {
		FSDirectory fsDir = FSDirectory.open(Paths.get(indexPath));
		// 1.启动时读取原有磁盘索引文件
//		Directory ramDir = new RAMDirectory(fsDir, new IOContext());
//		IndexWriterConfig config = new IndexWriterConfig(analyzer);
//		IndexWriter ramIndexWiter = new IndexWriter(ramDir, config);

		// 2.添加 Document
		Document doc = new Document();
		doc.add(new Field("id", document.getId(), StringField.TYPE_STORED));
		doc.add(new Field("content", document.getContent(), StringField.TYPE_STORED));
//		ramIndexWiter.addDocument(doc);
//		ramIndexWiter.close();
		
		// 3.退出时将内存索引保存到磁盘索引中
		IndexWriterConfig indexConfig = new IndexWriterConfig(analyzer);
		IndexWriter fsIndexWriter = new IndexWriter(fsDir, indexConfig);
//		fsIndexWriter.addIndexes(ramDir);
		fsIndexWriter.addDocument(doc);
		fsIndexWriter.close();
		return true;
	}
	
	public static boolean update(String indexPath, Term term, LuceneDocument document) throws Exception {
		FSDirectory fsDir = FSDirectory.open(Paths.get(indexPath));
		// 1.启动时读取原有磁盘索引文件
//		Directory ramDir = new RAMDirectory(fsDir, new IOContext());
//		IndexWriterConfig config = new IndexWriterConfig(analyzer);
//		IndexWriter ramIndexWiter = new IndexWriter(ramDir, config);

		// 2.添加 Document
		Document doc = new Document();
		doc.add(new Field("id", document.getId(), StringField.TYPE_STORED));
		doc.add(new Field("content", document.getContent(), StringField.TYPE_STORED));
//		ramIndexWiter.updateDocument(term, doc);
//		ramIndexWiter.close();
		
		// 3.退出时将内存索引保存到磁盘索引中
		IndexWriterConfig indexConfig = new IndexWriterConfig(analyzer);
		IndexWriter fsIndexWriter = new IndexWriter(fsDir, indexConfig);
		fsIndexWriter.updateDocument(term, doc);
		fsIndexWriter.close();
		return true;
	}

	public static boolean delete(String indexPath, Term term) throws Exception {
		FSDirectory fsDir = FSDirectory.open(Paths.get(indexPath));
		// 1.启动时读取原有磁盘索引文件
//		Directory ramDir = new RAMDirectory(fsDir, new IOContext());
//		IndexWriterConfig config = new IndexWriterConfig(analyzer);
//		IndexWriter ramIndexWiter = new IndexWriter(ramDir, config);
//
//		ramIndexWiter.deleteDocuments(term);
//		ramIndexWiter.commit();
//		ramIndexWiter.close();
		
		// 3.退出时将内存索引保存到磁盘索引中
		IndexWriterConfig indexConfig = new IndexWriterConfig(analyzer);
		IndexWriter fsIndexWriter = new IndexWriter(fsDir, indexConfig);
		fsIndexWriter.deleteDocuments(term);
		fsIndexWriter.commit();
		fsIndexWriter.close();
		return true;
	}
	
	public static List<String> search(String indexPath, String searchStr, int page, int rows) throws Exception {
		searchStr = ".*"+searchStr+".*";
		FSDirectory fsDir = FSDirectory.open(Paths.get(indexPath));
		IndexReader reader = null;
		reader = DirectoryReader.open(fsDir);
		IndexSearcher searcher = new IndexSearcher(reader);
//		String[] queries = { searchStr, searchStr, searchStr, searchStr, searchStr };//关键字
//		String[] fields = { "title", "summary", "content", "author", "tag" };//所在对应Fields
//		//BooleanClause.Occur.SHOULD,---关系或
//		//BooleanClause.Occur.MUST,---关系且
//		//BooleanClause.Occur.MUST_NOT---关系非
//		BooleanClause.Occur[] clauses = { BooleanClause.Occur.SHOULD,
//			     BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD, 
//			     BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD };
//		Query query = MultiFieldQueryParser.parse(queries, fields, clauses, analyzer);
		
//		QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);
//		Query query = queryParser.parse(searchStr);
		
//		Term term=new Term("title", searchStr);
//		FuzzyQuery query=new FuzzyQuery(term);
		
		RegexpQuery query=new RegexpQuery(new Term("content", searchStr));
		TopDocs topDocs = searcher.search(query, Integer.MAX_VALUE);
//		System.out.println("总共有【" + topDocs.totalHits + "】条匹配结果");
		List<String> listId = new ArrayList<String>();
		if(page <= 0) {
			page = 1;
		}
		if(rows <= 0) {
			rows = 20;
		}
//		int grow = 1;
//		if(topDocs.totalHits % rows == 0) {
//			grow = 0;
//		}
//		if(page > (topDocs.totalHits/rows)+grow) {
//			page = (topDocs.totalHits/rows)+grow;
//		}
		int start = (page-1) * rows;
		int end = Math.min(rows, topDocs.totalHits);
		// 循环读出前10条
		for (int i = start; i < end; i++) {
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			int docSn = scoreDoc.doc; // 文档内部编号
			Document doc = searcher.doc(docSn); // 根据编号取出相应的文档
			String id = doc.get("id");
			String content = doc.get("content");
			listId.add(id);
		}
		return listId;
	}
}
