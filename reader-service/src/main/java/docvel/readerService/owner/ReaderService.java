package docvel.readerService.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReadersRepo readers;

    private void noReaderById(long id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Reader with id %d not found", id));
    }

    public List<Reader> showAllReader(){
        return readers.findAll();
    }

    public Reader showReaderById(long readerId){
        if(readers.findById(readerId).isEmpty()) noReaderById(readerId);
        return readers.findById(readerId).get();
    }

    public Reader showReaderByName(String name){
        if(readers.findReaderByName(name).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Reader named %s not found", name));
        }
        return readers.findReaderByName(name).get();
    }

    public Reader createNewReader(Reader reader){
        return readers.save(reader);
    }

    public void deleteReader(long readerId){
        if(readers.findById(readerId).isEmpty()) noReaderById(readerId);
        readers.deleteById(readerId);
    }

    public Reader updateReader(long readerId, Reader newReader){
        if(readers.findById(readerId).isEmpty()) noReaderById(readerId);
        Reader reader = readers.findById(readerId).get();
        reader.setName(newReader.getName());
        return readers.save(reader);
    }
}
