INSERT INTO `nozamadb`.`user` (`userid`, `email`, `fullname`, `gender`, `mobile`, `usertype`) VALUES ('1', 'john@test.com', 'John', '0', '9898989090', '0');
INSERT INTO `nozamadb`.`user` (`userid`, `email`, `fullname`, `gender`, `mobile`, `usertype`) VALUES ('2', 'ram@test.com', 'ram', '0', '9898989091', '1');
INSERT INTO `nozamadb`.`user` (`userid`, `email`, `fullname`, `gender`, `mobile`, `usertype`) VALUES ('3', 'babu@test.com', 'Babu', '0', '9898989092', '2');
INSERT INTO `nozamadb`.`user` (`userid`, `email`, `fullname`, `gender`, `mobile`, `usertype`) VALUES ('4', 'lokesh@test.com', 'Lokesh', '0', '9898989090', '3');
INSERT INTO `nozamadb`.`user` (`userid`, `email`, `fullname`, `gender`, `mobile`, `usertype`) VALUES ('5', 'mary@test.com', 'Mary', '1', '9898989090', '4');

INSERT INTO `nozamadb`.`user` (`userid`, `email`, `fullname`, `mobile`, `password`, `usertype`) VALUES ('1291', 'chennai.warehouse@nozama.in', 'CHENNAI_WAREHOUSE', '4490939203', '$2a$10$048r/t.llB7cfhAwaTij7.kSNL5AEt/qStDNI/ihSBjUWvjyjQhh6', '2');

INSERT INTO `nozamadb`.`user` (`userid`, `email`, `fullname`, `mobile`, `password`, `usertype`) VALUES ('1292', 'noida.warehouse@nozama.in', 'NOIDA_WAREHOUSE', '9829384999', '$2a$10$048r/t.llB7cfhAwaTij7.kSNL5AEt/qStDNI/ihSBjUWvjyjQhh6', '2');

INSERT INTO `nozamadb`.`warehouse` (`warehouseid`, `warehouse_city`, `warehoust_pincode`, `userid`) VALUES ('1001', 'CHENNAI', '600028', '1291');

INSERT INTO `nozamadb`.`user` (`userid`, `email`, `fullname`, `mobile`, `password`, `usertype`) VALUES ('1293', 'bengaluru.warehouse@nozama.in', 'BENGALURU_WAREHOUSE', '9829384990', '$2a$10$048r/t.llB7cfhAwaTij7.kSNL5AEt/qStDNI/ihSBjUWvjyjQhh6', '2');

INSERT INTO `nozamadb`.`warehouse` (`warehouseid`, `warehouse_city`, `warehoust_pincode`, `userid`) VALUES ('1002', 'BENGALURU', '506100', '1292');

INSERT INTO `nozamadb`.`user` (`userid`, `email`, `fullname`, `mobile`, `password`, `usertype`) VALUES ('1294', 'gujarat.warehouse@nozama.in', 'GUJARAT_WAREHOUSE', '9829384991', '$2a$10$048r/t.llB7cfhAwaTij7.kSNL5AEt/qStDNI/ihSBjUWvjyjQhh6', '2');

INSERT INTO `nozamadb`.`warehouse` (`warehouseid`, `warehouse_city`, `warehoust_pincode`, `userid`) VALUES ('1003', 'GUJARAT', '395003', '1293');
