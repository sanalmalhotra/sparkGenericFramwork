package utils

import java.io.{FileInputStream, FileNotFoundException, IOException}

import main.test.{config, propFilePath}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

class PropertiesUtils {

  def loadProperties() {
    try {

      if(propFilePath != null || !("").equals(propFilePath)) {

        if(propFilePath.startsWith("abfss:")){
          val hdfsConf = new Configuration();
          val fs = FileSystem.get(hdfsConf);
          //THe file name contains absolute path of file
          val is = fs.open(new Path(propFilePath));

          //load properties
          config.load(is)

        }else{
          val input = new FileInputStream(propFilePath)
          config.load(input)
        }
        // val fileName = SparkFiles.get(propFilePath)

      }
    } catch {
      case e: FileNotFoundException =>
        e.printStackTrace()

      // System.exit(1)
      case e: IOException =>
        e.printStackTrace()


      case e: Exception =>
        e.printStackTrace()

    }
  }

}