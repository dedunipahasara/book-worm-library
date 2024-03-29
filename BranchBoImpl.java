package bo.impl;


import bo.custom.BranchBo;
import dao.custom.AdminDao;
import dao.custom.BranchDao;
import dao.util.DaoFactory;
import dto.BranchDto;
import etntity.Admin;
import etntity.Branch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchBoImpl implements BranchBo {
    private BranchDao branchDao = (BranchDao) DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.BRANCH);
    private AdminDao adminDao = (AdminDao) DaoFactory.getDaoFactory().getDao(DaoFactory.DaoTypes.ADMIN);

    @Override
    public void saveBranch(BranchDto dto) throws SQLException {
        Admin admin = adminDao.getbyId(dto.getAdminId());
        try {
            branchDao.save(new Branch(dto.getBranchName(), dto.getContact(), dto.getAddress(), admin));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BranchDto getBranch(String name) throws SQLException {
       Branch branch = branchDao.get(name);
       return new BranchDto(branch.getBranchId(), branch.getBranchName(), branch.getContact(), branch.getAddress(), branch.getAdmin().getAdminId());
    }

    @Override
    public List<BranchDto> getAllBranches() throws SQLException {
       List<Branch> branches = branchDao.loadAll();
       List<BranchDto> dtos = new ArrayList<>();
       for (Branch branch : branches){
           dtos.add(new BranchDto(branch.getBranchId(), branch.getBranchName(), branch.getContact(), branch.getAddress(), branch.getAdmin().getAdminId()));
       }
       return dtos;
    }

    @Override
    public void updateBranch(BranchDto branchDto) throws SQLException {
        Admin admin = adminDao.getbyId(branchDto.getAdminId());
        branchDao.update(new Branch(branchDto.getBranchId(), branchDto.getBranchName(), branchDto.getContact(), branchDto.getAddress(),admin ));
    }

    @Override
    public void deleteBranch(int branchId) throws SQLException {
              branchDao.delete(branchId);
    }

    @Override
    public BranchDto getBranchById(int branchId) throws SQLException {
       Branch branch = branchDao.getbyId(branchId);
       return new BranchDto(branch.getBranchId(),branch.getBranchName(), branch.getContact(), branch.getAddress(), branch.getBranchId());
    }
}
