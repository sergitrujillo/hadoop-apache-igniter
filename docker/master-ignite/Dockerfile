FROM sta/hadoop-cluster-base

WORKDIR /root


# install Ignite
# install Apache Ignite Hadoop Accelerator 2.6.0
RUN apt-get install -y unzip
RUN wget https://archive.apache.org/dist/ignite/2.6.0/apache-ignite-hadoop-2.6.0-bin.zip && \
    unzip -q apache-ignite-hadoop-2.6.0-bin.zip && \
    mv apache-ignite-hadoop-2.6.0-bin /usr/local/ignite && \
    rm apache-ignite-hadoop-2.6.0-bin.zip
ENV IGNITE_HOME /usr/local/ignite

RUN ln -s $IGNITE_HOME/libs/ignite-core-2.6.0.jar \
    $IGNITE_HOME/libs/ignite-shmem-1.0.0.jar \
    $IGNITE_HOME/libs/ignite-hadoop/ignite-hadoop-2.6.0.jar \
    $HADOOP_HOME/share/hadoop/common/lib

COPY config/* /tmp/
RUN mv /tmp/default-config.xml  $IGNITE_HOME/config && \
    mv /tmp/bootstrap.sh /etc/bootstrap.sh && \
    mv /tmp/core-site.xml $HADOOP_HOME/etc/hadoop/core-site.xml && \
    mv /tmp/mapred-site.xml $HADOOP_HOME/etc/hadoop/mapred-site.xml

RUN chown root:root /etc/bootstrap.sh
RUN chmod 700 /etc/bootstrap.sh

ENV BOOTSTRAP /etc/bootstrap.sh

CMD ["/etc/bootstrap.sh", "-d"]

#Ignite port
EXPOSE 11211