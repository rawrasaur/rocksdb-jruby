package io.burt.rocksdb;

import org.rocksdb.RocksDB;

import org.jruby.Ruby;
import org.jruby.RubyModule;
import org.jruby.RubyClass;
import org.jruby.runtime.load.Library;

public class RocksDbLibrary implements Library {
  public void load(Ruby ruby, boolean wrap) {
    RocksDB.loadLibrary();
    RubyModule rocksDbModule = RocksDb.install(ruby);
    Db.install(ruby, rocksDbModule);
    Batch.install(ruby, rocksDbModule);
    Snapshot.install(ruby, rocksDbModule);
    Iterator.install(ruby, rocksDbModule);
    installErrors(ruby, rocksDbModule);
  }

  private void installErrors(Ruby ruby, RubyModule parentModule) {
    RubyClass standardErrorClass = ruby.getStandardError();
    RubyClass errorClass = parentModule.defineClassUnder("Error", standardErrorClass, standardErrorClass.getAllocator());
    parentModule.defineClassUnder("IoError", errorClass, standardErrorClass.getAllocator());
    parentModule.defineClassUnder("InvalidArgumentError", errorClass, standardErrorClass.getAllocator());
  }
}
