package com.application.vocealuga.service.impl;

import com.application.vocealuga.dto.TransactionDto;
import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.entity.Funcionario;
import com.application.vocealuga.entity.TransactionEntity;
import com.application.vocealuga.entity.Veiculo;
import com.application.vocealuga.repository.ClienteRepository;
import com.application.vocealuga.repository.FuncionarioRepository;
import com.application.vocealuga.repository.TransactionRepository;
import com.application.vocealuga.repository.VeiculoRepository;
import com.application.vocealuga.service.TransactionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private ClienteRepository clienteRepository;
    private FuncionarioRepository funcionarioRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, ClienteRepository clienteRepository, FuncionarioRepository funcionarioRepository) {
        this.transactionRepository = transactionRepository;
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<TransactionEntity> findAll() {
        return transactionRepository.findAll();
    }

    public void saveTransaction(TransactionDto transactionDto) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setForma(transactionDto.getForma());
        transactionEntity.setDescricao(transactionDto.getDescricao());
        ClienteEntity cliente = clienteRepository.findByCpf(transactionDto.getIdCliente());

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }
        transactionEntity.setCliente(cliente);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null) {
            Funcionario funcionario;
            funcionario = funcionarioRepository.findByDocumento(username);
            transactionEntity.setFuncionario(funcionario);
        } else {
            throw new RuntimeException("Funcionário não encontrado");
        }

        try {
            transactionRepository.save(transactionEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTransaction(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).orElseThrow();
        transactionRepository.delete(transactionEntity);
    }

    public void updateTransaction() {
        return;
    }
}
