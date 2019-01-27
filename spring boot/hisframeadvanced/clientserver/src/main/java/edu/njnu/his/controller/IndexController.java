package edu.njnu.his.controller;

import edu.njnu.his.model.MedicalRecord;
import edu.njnu.his.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 将客户请求，调用不同的微服务获取数据返回给客户
 */
@RestController
public class IndexController {

	@Autowired	//自动从ioc容器中获得对应类型的bean的实例
	private PatientService patientService;	//		private RemoteService remoteService;
	/**
	 * 根据病历号返回病历并且包含病历的处方信息和医药信息
	 */
	@GetMapping("/mr/{code}")
	public MedicalRecord getMr(@PathVariable("code") String code){
		return patientService.getMr(code);
	}
}
