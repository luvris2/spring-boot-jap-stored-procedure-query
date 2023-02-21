package org.example.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.example.entity.ProcedureResultSet;

import java.util.ArrayList;
import java.util.List;

public class JpaQuery {
    private static List<ProcedureResultSet> executeNamedQuery() {
        EntityManager em = JpaUtils.getEntityManager();

        /* 입출력 파라미터가 없는 프로시저 */
        System.out.println("************************************************************");
        System.out.println("입출력 파라미터가 없는 프로시저 호출하기");
        System.out.println("테이블의 모든 데이터에 대한 조회 결과 확인");

        // 저장 프로시저 쿼리, 저장 프로시저의 이름 입력
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("p_select_all", ProcedureResultSet.class);

        // 쿼리의 결과를 리스트의 형태로 반환
        List<ProcedureResultSet> resultList = storedProcedure.getResultList();

        // 쿼리 결과를 저장할 변수 선언
        ArrayList<ProcedureResultSet> resultAll  = new ArrayList<>();

        int i=0; // Object Index
        for ( ProcedureResultSet row : resultList ) {
            // 저장
            resultAll.add(row);

            // 값 확인
            System.out.println("    프로시저 처리 결과 : " + (i+1) + " row = "
                    + resultAll.get(i).getUsername() + ", "
                    + resultAll.get(i).getAddress());
            i++;
        }


        /* 입력 파라미터가 있는 프로시저 */
        System.out.println("************************************************************");
        System.out.println("입력 파라미터가 있는 프로시저 호출하기");
        System.out.println("입력 파라미터는 특정 ID를 입력하여 해당 ID의 정보 확인");

        // 저장 프로시저 쿼리, 저장 프로시저의 이름 입력
        StoredProcedureQuery storedProcedureOnlyInput = em.createStoredProcedureQuery("p_select_id", ProcedureResultSet.class);

        // 저장 프로시저의 입력 파라미터 설정
        storedProcedureOnlyInput.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);

        // 저장 프로시저의 입력 파라미터 입력(순서, 값)
        storedProcedureOnlyInput.setParameter(1, 2);

        // 쿼리의 결과를 리스트의 형태로 반환
        List<ProcedureResultSet> result = storedProcedureOnlyInput.getResultList();

        int j=0; // Object Index
        for ( ProcedureResultSet row : result ) {
            // 값 확인
            System.out.println("    프로시저 처리 결과 : 입력한 ID(2), 조회 결과 : "
                    + result.get(j).getUsername() + ", "
                    + result.get(j).getAddress());
            j++;
        }


        /* 출력 파라미터가 있는 프로시저 */
        System.out.println("************************************************************");
        System.out.println("출력 파라미터가 있는 프로시저 호출하기");
        System.out.println("출력 파라미터는 특정 지역에 거주하는 사용자의 이름을 반환");

        // 저장 프로시저 쿼리, 저장 프로시저의 이름 입력
        StoredProcedureQuery storedProcedureOutput = em.createStoredProcedureQuery("p_select_address");

        // 저장 프로시저의 입력, 출력 파라미터 설정
        storedProcedureOutput.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        storedProcedureOutput.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);

        // 저장 프로시저의 입력 파라미터 입력(순서, 값)
        storedProcedureOutput.setParameter(1, "incheon");

        // 출력 파라미터의 값 저장
        String outputParam = (String) storedProcedureOutput.getOutputParameterValue(2);

        // 출력 파라미터의 값 확인
        System.out.println("    프로시저 처리 결과 : 입력한 지역 = incheon, 출력 파라미터의 값 = " + outputParam);
        System.out.println("************************************************************");


        em.close();
        return null;
    }

    public static List<ProcedureResultSet> doNameQuery() {
        return executeNamedQuery();
    }

}