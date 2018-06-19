package com.hieupham.data.data;

import com.google.gson.internal.LinkedTreeMap;
import com.hieupham.data.model.AssetData;
import com.hieupham.data.model.BlockData;
import com.hieupham.data.model.TransactionData;
import com.hieupham.data.source.remote.api.response.TransactionResponse;
import com.hieupham.data.source.remote.api.response.TransactionsResponse;
import com.hieupham.domain.entity.Asset;
import com.hieupham.domain.entity.Block;
import com.hieupham.domain.entity.CompositeTransaction;
import com.hieupham.domain.entity.CompositeTransactions;
import com.hieupham.domain.entity.Transaction;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hieupham on 6/6/18.
 */

public class DataProvider {

    public static final long BLOCK_NUMBER = 9999;

    public static TransactionData transactionData1() {
        return new TransactionData(
                "e300863b8006028796b2f60c549a7fc3d6433b685a3eff395fcc603dbccd520e",
                "eXs95fgZXwxVmMRQTsH4Qysk9joYag6D3VpM2ec2Lon27mVCeq",
                "239a6e306fb391265501f3bdd0e592090b8de7621f06cdad534307b9a246e03506bfe5a3c2bc94c3e07a3478edfcfc574a1f8bf50848981d6346ca7a28c21d82",
                "head", "confirmed", 8751, 1, 1400414, null,
                "d43394feea6fe9dcae0e2ec24f3190ee86079d1f481a0246dc4e0085c81dade50bc05aa3e76df0df0eeaf93e1da2a781",
                "f37c290b2ea923c78a8ab8081a6c9682e7d7d32888a9db89bb8c060d60f3343c",
                "f37c290b2ea923c78a8ab8081a6c9682e7d7d32888a9db89bb8c060d60f3343c");
    }

    public static Transaction transaction1() {
        return new Transaction("e300863b8006028796b2f60c549a7fc3d6433b685a3eff395fcc603dbccd520e",
                "eXs95fgZXwxVmMRQTsH4Qysk9joYag6D3VpM2ec2Lon27mVCeq",
                "239a6e306fb391265501f3bdd0e592090b8de7621f06cdad534307b9a246e03506bfe5a3c2bc94c3e07a3478edfcfc574a1f8bf50848981d6346ca7a28c21d82",
                "head", "confirmed", 8751, 1, 1400414, null,
                "d43394feea6fe9dcae0e2ec24f3190ee86079d1f481a0246dc4e0085c81dade50bc05aa3e76df0df0eeaf93e1da2a781",
                "f37c290b2ea923c78a8ab8081a6c9682e7d7d32888a9db89bb8c060d60f3343c",
                "f37c290b2ea923c78a8ab8081a6c9682e7d7d32888a9db89bb8c060d60f3343c");
    }

    public static TransactionData transactionData2() {
        return new TransactionData(
                "4769a210bf7e85af6e02044c113ca0c24097d92c30bb1b7348f8e944703e7072",
                "eXs95fgZXwxVmMRQTsH4Qysk9joYag6D3VpM2ec2Lon27mVCeq",
                "84098173eae5c53f830e55972111bd730abce622eed932c5beaf1704a647269e3bbb28aaeba716ca0e5bc3b1d8087dd9bf9b92d50c69d231046eaf3f5811ac75",
                "head", "confirmed", 8751, 2, 1400415, null,
                "d885b292a69d1ce1d90ae42fae88d158c3b6c50c3f11f568fcc7d16052699f02fb04372440abc742a7fd92cdc1283d95",
                "46f602bbcaa593b0d6315cf7e5bb997adf96ff1be855573345661526e3cda911",
                "46f602bbcaa593b0d6315cf7e5bb997adf96ff1be855573345661526e3cda911");
    }

    public static Transaction transaction2() {
        return new Transaction("4769a210bf7e85af6e02044c113ca0c24097d92c30bb1b7348f8e944703e7072",
                "eXs95fgZXwxVmMRQTsH4Qysk9joYag6D3VpM2ec2Lon27mVCeq",
                "84098173eae5c53f830e55972111bd730abce622eed932c5beaf1704a647269e3bbb28aaeba716ca0e5bc3b1d8087dd9bf9b92d50c69d231046eaf3f5811ac75",
                "head", "confirmed", 8751, 2, 1400415, null,
                "d885b292a69d1ce1d90ae42fae88d158c3b6c50c3f11f568fcc7d16052699f02fb04372440abc742a7fd92cdc1283d95",
                "46f602bbcaa593b0d6315cf7e5bb997adf96ff1be855573345661526e3cda911",
                "46f602bbcaa593b0d6315cf7e5bb997adf96ff1be855573345661526e3cda911");
    }

    public static TransactionData transactionData3() {
        return new TransactionData(
                "985b136bf7e147dada3e80d8a077bf489d59182a5b5d05a093158b25cd55f329",
                "eXs95fgZXwxVmMRQTsH4Qysk9joYag6D3VpM2ec2Lon27mVCeq",
                "722b9b99b8fd5fc9f450067b8d4540107f20abffeb65d482a07d9b4d47b288fa95c86cc1a8893000e291c6c31eb33ada5f7cd220705ad7819cdc082bc35c391d",
                "head", "confirmed", 8751, 3, 1400416, null,
                "4d55b81263edb6895e3d1245e1c56a7c81817745fbed79c90f47a629676619bf497fe4af90f9bb01af66c1c03901c437",
                "9125861bcfd1dde6e54ed1617539f5f36b27ef8a40c5e24c94eadc567f37ad65",
                "9125861bcfd1dde6e54ed1617539f5f36b27ef8a40c5e24c94eadc567f37ad65");
    }

    public static Transaction transaction3() {
        return new Transaction("985b136bf7e147dada3e80d8a077bf489d59182a5b5d05a093158b25cd55f329",
                "eXs95fgZXwxVmMRQTsH4Qysk9joYag6D3VpM2ec2Lon27mVCeq",
                "722b9b99b8fd5fc9f450067b8d4540107f20abffeb65d482a07d9b4d47b288fa95c86cc1a8893000e291c6c31eb33ada5f7cd220705ad7819cdc082bc35c391d",
                "head", "confirmed", 8751, 3, 1400416, null,
                "4d55b81263edb6895e3d1245e1c56a7c81817745fbed79c90f47a629676619bf497fe4af90f9bb01af66c1c03901c437",
                "9125861bcfd1dde6e54ed1617539f5f36b27ef8a40c5e24c94eadc567f37ad65",
                "9125861bcfd1dde6e54ed1617539f5f36b27ef8a40c5e24c94eadc567f37ad65");
    }

    public static TransactionData transactionData4() {
        return new TransactionData(
                "a2e5043dd6206b3dc49b727e72cb09feb3ce33453e685233f2233365acfb4754",
                "eXs95fgZXwxVmMRQTsH4Qysk9joYag6D3VpM2ec2Lon27mVCeq",
                "70368c8b800405ac3072c9312369e7ceb9fb66857755faee53c2c41265075abced91983a06c0158eb42777ca3e24d2f805ffccdca87695b74b52314cb7e47300",
                "head", "confirmed", 8751, 4, 1400417, null,
                "f5cb6b0ba18c3aa812931849e67c2bc8bbc8b529d4d4a03f11025e743681176965d6aefb906b15072152b7fc857f20d5",
                "f0dc06ddd6f4d3c747cfd2034804e1abc102268ee5324944c6bc63ea5a3f4cd5",
                "f0dc06ddd6f4d3c747cfd2034804e1abc102268ee5324944c6bc63ea5a3f4cd5");
    }

    public static Transaction transaction4() {
        return new Transaction("a2e5043dd6206b3dc49b727e72cb09feb3ce33453e685233f2233365acfb4754",
                "eXs95fgZXwxVmMRQTsH4Qysk9joYag6D3VpM2ec2Lon27mVCeq",
                "70368c8b800405ac3072c9312369e7ceb9fb66857755faee53c2c41265075abced91983a06c0158eb42777ca3e24d2f805ffccdca87695b74b52314cb7e47300",
                "head", "confirmed", 8751, 4, 1400417, null,
                "f5cb6b0ba18c3aa812931849e67c2bc8bbc8b529d4d4a03f11025e743681176965d6aefb906b15072152b7fc857f20d5",
                "f0dc06ddd6f4d3c747cfd2034804e1abc102268ee5324944c6bc63ea5a3f4cd5",
                "f0dc06ddd6f4d3c747cfd2034804e1abc102268ee5324944c6bc63ea5a3f4cd5");
    }

    public static TransactionData transactionData5() {
        return new TransactionData(
                "f3d7d98a404a187be18e31ab2f54969a09c13077c24219f5bbb886317ec21e68",
                "eXs95fgZXwxVmMRQTsH4Qysk9joYag6D3VpM2ec2Lon27mVCeq",
                "690157e4aa1fef489fdb3c369e98fce6ea0a00b23db133d3749fbac86a9480ab69f79e31e8b227d5dbfffb6aac4533b89486ffb974f28800c324f5a851267657",
                "head", "confirmed", 8751, 5, 1400418, null,
                "56a3592f216a7508002d05eef8e43df44a052474b6eb0278ff1b2f353e34dd726fecce99a24e7aa15f4d00f86e49d440",
                "8eea2ef5f9ddc7840e927a06c9c59b5cde5382c6247384d6f0cf4b795145d886",
                "8eea2ef5f9ddc7840e927a06c9c59b5cde5382c6247384d6f0cf4b795145d886");
    }

    public static Transaction transaction5() {
        return new Transaction("f3d7d98a404a187be18e31ab2f54969a09c13077c24219f5bbb886317ec21e68",
                "eXs95fgZXwxVmMRQTsH4Qysk9joYag6D3VpM2ec2Lon27mVCeq",
                "690157e4aa1fef489fdb3c369e98fce6ea0a00b23db133d3749fbac86a9480ab69f79e31e8b227d5dbfffb6aac4533b89486ffb974f28800c324f5a851267657",
                "head", "confirmed", 8751, 5, 1400418, null,
                "56a3592f216a7508002d05eef8e43df44a052474b6eb0278ff1b2f353e34dd726fecce99a24e7aa15f4d00f86e49d440",
                "8eea2ef5f9ddc7840e927a06c9c59b5cde5382c6247384d6f0cf4b795145d886",
                "8eea2ef5f9ddc7840e927a06c9c59b5cde5382c6247384d6f0cf4b795145d886");
    }

    public static AssetData assetData1() {
        LinkedTreeMap<String, String> metadata = new LinkedTreeMap<>();
        metadata.put("Creator", "fJFSAcJCzEkVe6otMg2EtDuJbkaRzuQiH1WdbLi9Hp1gEbCmKz");
        metadata.put("Created (date)", "2018 Mar 26 23:59:59");
        return new AssetData(
                "690157e4aa1fef489fdb3c369e98fce6ea0a00b23db133d3749fbac86a9480ab69f79e31e8b227d5dbfffb6aac4533b89486ffb974f28800c324f5a851267657",
                "GWHOS_1gEbCmKz_2018_Mar_26_23_59_59_20208526",
                "01a068e9012cbf9f08d95b17a2d851c8233f2933daef4a507c73a5422d9f9c41579e77e2d6d5507dbf03270edd0f0c5d22210fd5f2a5f9ff7579b6b721fb865d1f",
                metadata, "fJFSAcJCzEkVe6otMg2EtDuJbkaRzuQiH1WdbLi9Hp1gEbCmKz", "confirmed", 8532,
                3, null, 12187);
    }

    public static Asset asset1() {
        LinkedTreeMap<String, String> metadata = new LinkedTreeMap<>();
        metadata.put("Creator", "fJFSAcJCzEkVe6otMg2EtDuJbkaRzuQiH1WdbLi9Hp1gEbCmKz");
        metadata.put("Created (date)", "2018 Mar 26 23:59:59");
        return new Asset(
                "690157e4aa1fef489fdb3c369e98fce6ea0a00b23db133d3749fbac86a9480ab69f79e31e8b227d5dbfffb6aac4533b89486ffb974f28800c324f5a851267657",
                "GWHOS_1gEbCmKz_2018_Mar_26_23_59_59_20208526",
                "01a068e9012cbf9f08d95b17a2d851c8233f2933daef4a507c73a5422d9f9c41579e77e2d6d5507dbf03270edd0f0c5d22210fd5f2a5f9ff7579b6b721fb865d1f",
                metadata, "fJFSAcJCzEkVe6otMg2EtDuJbkaRzuQiH1WdbLi9Hp1gEbCmKz", "confirmed", 8532,
                3, null, 12187);
    }

    public static AssetData assetData2() {
        LinkedTreeMap<String, String> metadata = new LinkedTreeMap<>();
        metadata.put("Creator", "fJFSAcJCzEkVe6otMg2EtDuJbkaRzuQiH1WdbLi9Hp1gEbCmKz");
        metadata.put("Created (date)", "2018 May 21 23:59:59");
        return new AssetData(
                "722b9b99b8fd5fc9f450067b8d4540107f20abffeb65d482a07d9b4d47b288fa95c86cc1a8893000e291c6c31eb33ada5f7cd220705ad7819cdc082bc35c391d",
                "GWHOS_PDi6scB1_2018_May_21_23_59_59_65564680",
                "01726065565e281f407af8fc9feab7a48006b0d1a168df4cfaf3ae4a6a5b1276938590ae69c44b1e0887d3784d9c2ddb0d2db08c86b30ada22afb8fd1641591797",
                metadata, "eGHQS6LMpR6GRPTy5XxZKQWeB1wwPf54fzjCmuaXwqPDi6scB1", "confirmed", 8537,
                1, null, 12202);
    }

    public static Asset asset2() {
        LinkedTreeMap<String, String> metadata = new LinkedTreeMap<>();
        metadata.put("Creator", "fJFSAcJCzEkVe6otMg2EtDuJbkaRzuQiH1WdbLi9Hp1gEbCmKz");
        metadata.put("Created (date)", "2018 May 21 23:59:59");
        return new Asset(
                "722b9b99b8fd5fc9f450067b8d4540107f20abffeb65d482a07d9b4d47b288fa95c86cc1a8893000e291c6c31eb33ada5f7cd220705ad7819cdc082bc35c391d",
                "GWHOS_PDi6scB1_2018_May_21_23_59_59_65564680",
                "01726065565e281f407af8fc9feab7a48006b0d1a168df4cfaf3ae4a6a5b1276938590ae69c44b1e0887d3784d9c2ddb0d2db08c86b30ada22afb8fd1641591797",
                metadata, "eGHQS6LMpR6GRPTy5XxZKQWeB1wwPf54fzjCmuaXwqPDi6scB1", "confirmed", 8537,
                1, null, 12202);
    }

    public static AssetData assetData3() {
        LinkedTreeMap<String, String> metadata = new LinkedTreeMap<>();
        metadata.put("Creator", "eGHQS6LMpR6GRPTy5XxZKQWeB1wwPf54fzjCmuaXwqPDi6scB1");
        metadata.put("Created (date)", "2018 May 25 23:59:59");
        return new AssetData(
                "239a6e306fb391265501f3bdd0e592090b8de7621f06cdad534307b9a246e03506bfe5a3c2bc94c3e07a3478edfcfc574a1f8bf50848981d6346ca7a28c21d82",
                "GWHOS_PDi6scB1_2018_May_25_23_59_59_05801098",
                "01bf73828ffa22bac28b59526523d7549ba88f201af795b8df80b493458e3303410f75313303e15c1c27c381a8fba373e3c6244200290073e7fab4d9cb94f098f5",
                metadata, "eGHQS6LMpR6GRPTy5XxZKQWeB1wwPf54fzjCmuaXwqPDi6scB1", "confirmed", 8538,
                1, null, 12205);
    }

    public static Asset asset3() {
        LinkedTreeMap<String, String> metadata = new LinkedTreeMap<>();
        metadata.put("Creator", "eGHQS6LMpR6GRPTy5XxZKQWeB1wwPf54fzjCmuaXwqPDi6scB1");
        metadata.put("Created (date)", "2018 May 25 23:59:59");
        return new Asset(
                "239a6e306fb391265501f3bdd0e592090b8de7621f06cdad534307b9a246e03506bfe5a3c2bc94c3e07a3478edfcfc574a1f8bf50848981d6346ca7a28c21d82",
                "GWHOS_PDi6scB1_2018_May_25_23_59_59_05801098",
                "01bf73828ffa22bac28b59526523d7549ba88f201af795b8df80b493458e3303410f75313303e15c1c27c381a8fba373e3c6244200290073e7fab4d9cb94f098f5",
                metadata, "eGHQS6LMpR6GRPTy5XxZKQWeB1wwPf54fzjCmuaXwqPDi6scB1", "confirmed", 8538,
                1, null, 12205);
    }

    public static AssetData assetData4() {
        LinkedTreeMap<String, String> metadata = new LinkedTreeMap<>();
        metadata.put("Creator", "eGHQS6LMpR6GRPTy5XxZKQWeB1wwPf54fzjCmuaXwqPDi6scB1");
        metadata.put("Created (date)", "2018 May 23 23:59:59");
        return new AssetData(
                "70368c8b800405ac3072c9312369e7ceb9fb66857755faee53c2c41265075abced91983a06c0158eb42777ca3e24d2f805ffccdca87695b74b52314cb7e47300",
                "GWHOS_PDi6scB1_2018_May_23_23_59_59_03647074",
                "01b59af83346c782745e56cfa8d54ae2707f3ca0606cb06d402c0eea705a895e2809c25f3d00495698682668efd97a5dbd8966feae8b2642e6d369e456d441bc24",
                metadata, "eGHQS6LMpR6GRPTy5XxZKQWeB1wwPf54fzjCmuaXwqPDi6scB1", "confirmed", 8547,
                1, null, 12229);
    }

    public static Asset asset4() {
        LinkedTreeMap<String, String> metadata = new LinkedTreeMap<>();
        metadata.put("Creator", "eGHQS6LMpR6GRPTy5XxZKQWeB1wwPf54fzjCmuaXwqPDi6scB1");
        metadata.put("Created (date)", "2018 May 23 23:59:59");
        return new Asset(
                "70368c8b800405ac3072c9312369e7ceb9fb66857755faee53c2c41265075abced91983a06c0158eb42777ca3e24d2f805ffccdca87695b74b52314cb7e47300",
                "GWHOS_PDi6scB1_2018_May_23_23_59_59_03647074",
                "01b59af83346c782745e56cfa8d54ae2707f3ca0606cb06d402c0eea705a895e2809c25f3d00495698682668efd97a5dbd8966feae8b2642e6d369e456d441bc24",
                metadata, "eGHQS6LMpR6GRPTy5XxZKQWeB1wwPf54fzjCmuaXwqPDi6scB1", "confirmed", 8547,
                1, null, 12229);
    }

    public static AssetData assetData5() {
        LinkedTreeMap<String, String> metadata = new LinkedTreeMap<>();
        metadata.put("Creator", "eHnD4zC6ao3dRD8ZJAD4njHQumqDwYjsBkxV9ayfDGwJ2wAGDu");
        metadata.put("Created (date)", "2018 May 27 23:59:59");
        return new AssetData(
                "84098173eae5c53f830e55972111bd730abce622eed932c5beaf1704a647269e3bbb28aaeba716ca0e5bc3b1d8087dd9bf9b92d50c69d231046eaf3f5811ac75",
                "GWHOS_wJ2wAGDu_2018_May_27_23_59_59_25515484",
                "0110c1fa2dbbe7853e0b2186d2dceffc5bc7dbfd8469ee2a2120178eda4eeadba482b401b3de2df992fe087c7af000def59ee3996a05cbb0186b2688934e960818",
                metadata, "eHnD4zC6ao3dRD8ZJAD4njHQumqDwYjsBkxV9ayfDGwJ2wAGDu", "confirmed", 8711,
                5, null, 15051);
    }

    public static Asset asset5() {
        LinkedTreeMap<String, String> metadata = new LinkedTreeMap<>();
        metadata.put("Creator", "eHnD4zC6ao3dRD8ZJAD4njHQumqDwYjsBkxV9ayfDGwJ2wAGDu");
        metadata.put("Created (date)", "2018 May 27 23:59:59");
        return new Asset(
                "84098173eae5c53f830e55972111bd730abce622eed932c5beaf1704a647269e3bbb28aaeba716ca0e5bc3b1d8087dd9bf9b92d50c69d231046eaf3f5811ac75",
                "GWHOS_wJ2wAGDu_2018_May_27_23_59_59_25515484",
                "0110c1fa2dbbe7853e0b2186d2dceffc5bc7dbfd8469ee2a2120178eda4eeadba482b401b3de2df992fe087c7af000def59ee3996a05cbb0186b2688934e960818",
                metadata, "eHnD4zC6ao3dRD8ZJAD4njHQumqDwYjsBkxV9ayfDGwJ2wAGDu", "confirmed", 8711,
                5, null, 15051);
    }

    public static BlockData blockData1() {
        return new BlockData(8751,
                "00622602a2633798bed7cdd0fe1510957710c85b6a48783c1c54d00350a10840",
                "2018-06-01T10:48:07.000000Z");
    }

    public static Block block1() {
        return new Block(8751, "00622602a2633798bed7cdd0fe1510957710c85b6a48783c1c54d00350a10840",
                "2018-06-01T10:48:07.000000Z");
    }

    public static List<Transaction> transactions1() {
        return new ArrayList<Transaction>() {{
            add(transaction1());
            add(transaction2());
            add(transaction3());
            add(transaction4());
            add(transaction5());
        }};
    }

    public static List<Asset> assets1() {
        return new ArrayList<Asset>() {{
            add(asset1());
            add(asset2());
            add(asset3());
            add(asset4());
            add(asset5());
        }};
    }

    public static List<Block> blocks1() {
        return new ArrayList<Block>() {{
            add(block1());
        }};
    }

    public static List<TransactionData> transactionDatas1() {
        return new ArrayList<TransactionData>() {{
            add(transactionData1());
            add(transactionData2());
            add(transactionData3());
            add(transactionData4());
            add(transactionData5());
        }};
    }

    public static List<AssetData> assetDatas1() {
        return new ArrayList<AssetData>() {{
            add(assetData1());
            add(assetData2());
            add(assetData3());
            add(assetData4());
            add(assetData5());
        }};
    }

    public static List<BlockData> blockDatas1() {
        return new ArrayList<BlockData>() {{
            add(blockData1());
        }};
    }

    public static CompositeTransactions compositeTransactions1() {
        return new CompositeTransactions(transactions1(), assets1(), blocks1());
    }

    public static CompositeTransaction compositeTransaction1() {
        return new CompositeTransaction(transaction1(), asset3(), block1());
    }

    public static TransactionsResponse transactionsResponse1() {
        return new TransactionsResponse(transactionDatas1(), assetDatas1(), blockDatas1());
    }

    public static TransactionResponse transactionResponse1() {
        return new TransactionResponse(transactionData1(), assetData3(), blockData1());
    }

    public static SQLException sqlException() {
        return new SQLException("SQLite exception has occurred");
    }

    public static SocketTimeoutException timeoutException() {
        return new SocketTimeoutException("Timeout Exception has occurred");
    }
}
