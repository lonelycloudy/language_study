my=Mysql.new('127.0.0.1','root','ictspace','raisdb')
st=my.prepare("insert into rails_test (title) values (?)")
st.execute("hello,ruby")
st.prepare("select id,title from rails_teset")
st.execute
st.fetch
st.close