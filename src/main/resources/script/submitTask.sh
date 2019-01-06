# !/bin/bash

/data/softWare/spark/bin/spark-submit \
  --master spark://localhost:7077 \
  --conf spark.ui.port=4052 \
  --deploy-mode client \
  --class com.spark.scala.study.sql.HiveTest \
  --total-executor-cores 2 \
  --executor-memory 1g \
  /data/StudySpace/SparkStudy/target/spark-1.0-SNAPSHOT-jar-with-dependencies.jar >submit.log 2>&1 &
