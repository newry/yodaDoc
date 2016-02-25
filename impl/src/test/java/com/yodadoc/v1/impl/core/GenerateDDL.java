package com.yodadoc.v1.impl.core;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.yodadoc.v1.core.entity.document.Document;
import com.yodadoc.v1.core.entity.folder.Folder;

public class GenerateDDL {

	public static void main(String[] args) {
		try {
			Configuration cfg = new Configuration();
			cfg.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
			cfg.setProperty(AvailableSettings.DRIVER, "org.postgresql.Driver");
			cfg.setProperty(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/yoda");
			cfg.setProperty(AvailableSettings.USER, "admin");
			cfg.setProperty(AvailableSettings.PASS, "admin");
			cfg.addAnnotatedClass(Document.class);
			cfg.addAnnotatedClass(Folder.class);
			SchemaExport export = new SchemaExport(cfg);
			export.setFormat(true);
			export.setDelimiter(";");
			export.setOutputFile("C:/work/dms/yodadoc/create.sql");
			export.create(true, true);
		} finally {
			System.exit(0);
		}
	}
}
