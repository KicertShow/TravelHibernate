package backend.hotel.model.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoggingService implements LoggingInterFaceService {
	@Autowired
	private LoggingInterFaceDao logDao;

	@Override
	public boolean checklog(Logging users) {
		return logDao.checklog(users);
	}

}
