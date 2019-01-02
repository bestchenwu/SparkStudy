# !/bin/bash

/data/softWare/spark/bin/spark-submit \
  --master spark://localhost:7077 \
  --conf spark.ui.port=4062 \
  --deploy-mode client \
  --class com.spark.scala.kakfa.Test010 \
  --total-executor-cores 2 \
  --executor-memory 1g \
  /data/StudySpace/SparkStudy/target/spark-1.0-SNAPSHOT-jar-with-dependencies.jar >test010.log 2>&1 &
